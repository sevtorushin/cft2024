package ru.cft;

import java.io.File;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        File inputFile = new File("E:\\test.txt");
        StringParser parser = new StringParser();
        LineMapper mapper = new LineMapper(parser);
        FileLineReader lineReader = new FileLineReader();
        List<String> linesOnStrings = lineReader.getLinesOnStrings(inputFile, Integer.MAX_VALUE);
        Map<StringType, List<String>> map = mapper.map(linesOnStrings);
        System.out.println(map);

        FileManager fileManager = new FileManager("", "e:\\");
        fileManager.allocate(map);
    }
}
