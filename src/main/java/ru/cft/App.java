package ru.cft;

import ru.cft.statictics.NumberStatistic;
import ru.cft.statictics.StringStatistic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        File inputFile = new File("E:\\test.txt");
        StringParser parser = new StringParser();
        LineMapper mapper = new LineMapper(parser);
        FileLineReader lineReader = new FileLineReader();
        NumberStatistic integerStatistic = new NumberStatistic(StringType.INTEGER);
        NumberStatistic floatStatistic = new NumberStatistic(StringType.FLOAT);
        StringStatistic stringStatistic = new StringStatistic(StringType.STRING);
        FileManager fileManager = new FileManager("result_", "c:\\Program Files", false, "txt");
        List<String> linesOnStrings = lineReader.getLinesOnStringsRange(inputFile, 1, 30);
        Map<StringType, List<String>> map = mapper.map(linesOnStrings);
        integerStatistic.refresh(map.get(StringType.INTEGER));
        floatStatistic.refresh(map.get(StringType.FLOAT));
        stringStatistic.refresh(map.get(StringType.STRING));
        integerStatistic.getShortStatistic();
        floatStatistic.getShortStatistic();
        stringStatistic.getShortStatistic();
        integerStatistic.getFullStatistic();
        floatStatistic.getFullStatistic();
        stringStatistic.getFullStatistic();
        fileManager.allocate(map);
    }
}
