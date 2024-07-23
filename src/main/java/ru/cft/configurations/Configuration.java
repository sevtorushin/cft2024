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

import java.util.Optional;

@org.springframework.context.annotation.Configuration
@ComponentScan("ru.cft.service")
@RequiredArgsConstructor
public class Configuration {

    @Bean
    public FileManager fileManager(CommandLine cmd, FileLineWriter fileLineWriter) {
        Optional<String> filesPath = Optional.ofNullable(cmd.getOptionValue("o"));
        Optional<String> filesPrefix = Optional.ofNullable(cmd.getOptionValue("p"));
        boolean appendMode = cmd.hasOption("a");
        FileManager fileManager = new FileManager(
                filesPrefix.orElse(""), filesPath.orElse(""), appendMode, "txt");
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
    public CommandLine cmd() {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(cliOptions(), ApplicationRunner.getArgs());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return cmd;
    }

    public Options cliOptions() {
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
