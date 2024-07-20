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
        FileManager fileManager = new FileManager("result_", "e:\\new", true, fileExtention);
        List<String> linesOnStrings = lineReader.getLinesOnStringsRange(inputFile, 1, Integer.MAX_VALUE);
        Map<StringType, List<String>> map = mapper.map(linesOnStrings);
        fileManager.allocate(map);
    }
}
