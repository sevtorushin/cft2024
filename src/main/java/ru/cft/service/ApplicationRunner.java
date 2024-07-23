package ru.cft.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.cft.entity.StringType;
import ru.cft.service.statictics.NumberStatistic;
import ru.cft.service.statictics.StringStatistic;

import java.io.File;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ApplicationRunner {

    @Getter
    private static String[] args;
    private final CommandLine cmd;
    private final LineMapper mapper;
    private final FileLineReader lineReader;
    private final FileManager fileManager;
    private final NumberStatistic integerStatistic;
    private final NumberStatistic floatStatistic;
    private final StringStatistic stringStatistic;

    private static final Logger log = LogManager.getLogger(ApplicationRunner.class.getSimpleName());

    public static ApplicationRunner init(Class<?> configurationClass, String[] args) {
        ApplicationRunner.args = args;
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(configurationClass);
        return springContext.getBean(ApplicationRunner.class);
    }

    public void start() {
        List<String> filePaths = cmd.getArgList();
        if (filePaths.isEmpty()) {
            log.warn("No input file specified");
            return;
        }
        long fromStringNumber = 1L;
        long stringAmount = 1000_000L;
        for (String filepath : filePaths) {
            File file = new File(filepath);
            if (!file.exists()) {
                log.warn(String.format("Input file %s not exists", file));
                return;
            }
            List<String> readStrings;
            Map<StringType, List<String>> map;
            do {
                readStrings = lineReader.getLinesOnStringsRange(file, fromStringNumber, stringAmount);
                map = mapper.map(readStrings);
                integerStatistic.refresh(map.get(StringType.INTEGER));
                floatStatistic.refresh(map.get(StringType.FLOAT));
                stringStatistic.refresh(map.get(StringType.STRING));
                fileManager.allocate(map);
                fromStringNumber += stringAmount;
            } while (!readStrings.isEmpty());
            fromStringNumber = 1L;
        }
        if (cmd.hasOption("s")) {
            integerStatistic.getShortStatistic();
            floatStatistic.getShortStatistic();
            stringStatistic.getShortStatistic();
        } else if (cmd.hasOption("f")) {
            integerStatistic.getFullStatistic();
            floatStatistic.getFullStatistic();
            stringStatistic.getFullStatistic();
        }
    }
}
