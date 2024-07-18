package ru.cft;

import java.io.File;

public class App {
    public static void main(String[] args) {
        FileLineReader lineReader = new FileLineReader();
        File file = new File("E:\\Work\\Scientific_Work\\TUSUR\\Libraries\\Current_Article\\Introduction.txt");
//        ByteBuffer buffer = lineReader.readFile(0, 2);
        System.out.println(lineReader.getLinesOnBytes(file,201));
        System.out.println(lineReader.getLinesOnStrings(file,4));
        System.out.println(lineReader.getLinesOnByteRange(file,2,201));
        System.out.println(lineReader.getLinesOnStringsRange(file,1,2));
    }
}
