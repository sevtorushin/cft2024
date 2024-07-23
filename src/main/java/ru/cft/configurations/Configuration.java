package ru.cft.configurations;

import lombok.RequiredArgsConstructor;
import org.apache.commons.cli.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.cft.entity.StringType;
import ru.cft.service.ApplicationRunner;
import ru.cft.service.FileLineWriter;
import ru.cft.service.FileManager;
import ru.cft.service.statictics.NumberStatistic;
import ru.cft.service.statictics.StringStatistic;

@org.springframework.context.annotation.Configuration
@ComponentScan("ru.cft.service")
@RequiredArgsConstructor
public class Configuration {

    @Bean
    public FileManager fileManager(CommandLine cmd, FileLineWriter fileLineWriter) {
        String filesPath = cmd.getOptionValue("o");
        String filesPrefix = cmd.getOptionValue("p");
        boolean appendMode = cmd.hasOption("a");
        FileManager fileManager = new FileManager(filesPrefix, filesPath, appendMode, "txt");
        fileManager.setWriter(fileLineWriter);
        return fileManager;
    }

    @Bean
    public NumberStatistic integerStatistic() {
        return new NumberStatistic(StringType.INTEGER);
    }

    @Bean
    public NumberStatistic floatStatistic() {
        return new NumberStatistic(StringType.FLOAT);
    }

    @Bean
    public StringStatistic stringStatistic() {
        return new StringStatistic(StringType.STRING);
    }

    @Bean
    public CommandLine cmd(){
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(cliOptions(), ApplicationRunner.getArgs());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cmd;
    }

    public Options cliOptions(){
        Options options = new Options();
        Option pathOption = new Option("o", true, "Path of output files");
        Option prefixOption = new Option("p", true, "Prefix of output files");
        Option appendOption = new Option("a", false, "Append to output files");
        Option shortStatisticOption = new Option("s", false, "Short statistic");
        Option fullStatisticOption = new Option("f", false, "Full statistic");
        options.addOption(pathOption);
        options.addOption(prefixOption);
        options.addOption(appendOption);
        options.addOption(shortStatisticOption);
        options.addOption(fullStatisticOption);
        return options;
    }
}
