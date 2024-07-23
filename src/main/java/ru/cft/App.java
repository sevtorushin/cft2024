package ru.cft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cft.configurations.Configuration;
import ru.cft.service.ApplicationRunner;
import ru.cft.service.FileManager;

public class App {
    public static void main(String[] args) {
        ApplicationRunner runner = new ApplicationRunner(Configuration.class, args);
        runner.run();

//        File inputFile = new File("E:\\test.txt");
//        StringParser parser = new StringParser();
//        LineMapper mapper = new LineMapper(parser);
//        FileLineReader lineReader = new FileLineReader();
//        NumberStatistic integerStatistic = new NumberStatistic(StringType.INTEGER);
//        NumberStatistic floatStatistic = new NumberStatistic(StringType.FLOAT);
//        StringStatistic stringStatistic = new StringStatistic(StringType.STRING);
//        FileManager fileManager = new FileManager("result_", "c:\\Program Files", false, "txt");
//        List<String> linesOnStrings = lineReader.getLinesOnStringsRange(inputFile, 1, 30);
//        Map<StringType, List<String>> map = mapper.map(linesOnStrings);
//        integerStatistic.refresh(map.get(StringType.INTEGER));
//        floatStatistic.refresh(map.get(StringType.FLOAT));
//        stringStatistic.refresh(map.get(StringType.STRING));
//        integerStatistic.getShortStatistic();
//        floatStatistic.getShortStatistic();
//        stringStatistic.getShortStatistic();
//        integerStatistic.getFullStatistic();
//        floatStatistic.getFullStatistic();
//        stringStatistic.getFullStatistic();
//        fileManager.allocate(map);
    }
}
