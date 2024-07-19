package ru.cft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
//        File inputFile = new File("E:\\test.txt");
//        File outputFile = new File("E:\\testOut.txt");
//        FileLineReader lineReader = new FileLineReader();
//        FileLineWriter lineWriter = new FileLineWriter();
//        StringParser parser = new StringParser();
//        LineMapper mapper = new LineMapper(parser);
//        List<String> linesOnStrings = lineReader.getLinesOnStrings(inputFile, Integer.MAX_VALUE);
//        Map<StringType, List<String>> map = mapper.map(linesOnStrings);
//        System.out.println(map);
//        lineWriter.writeLines(outputFile, map.get(StringType.INTEGER));

//        FileManager fileManager = new FileManager();
//        fileManager.createFile("test3", "int", "e:/");


        File outputFile = new File("E:\\test2.txt");
        FileLineWriter writer = new FileLineWriter();
        writer.appendLines(outputFile, List.of("1","2", "3"));
    }
}
