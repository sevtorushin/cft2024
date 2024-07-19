package ru.cft;

import java.io.File;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        File file = new File("E:\\test.txt");
        FileLineReader lineReader = new FileLineReader();
        StringParser parser = new StringParser();
        LineMapper mapper = new LineMapper(parser);
        List<String> linesOnStrings = lineReader.getLinesOnStrings(file, Integer.MAX_VALUE);
        Map<StringType, List<String>> map = mapper.map(linesOnStrings);
        System.out.println(map);
    }
}
