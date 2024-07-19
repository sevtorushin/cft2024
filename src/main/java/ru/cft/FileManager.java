package ru.cft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileManager {

    public File createFile(String fileName, String prefix, String directory){
        if (fileName.contains("."))
            throw new IllegalArgumentException("Invalid file name");
        Path filePath = null;
        try {
            filePath = Files.createFile(Path.of(directory, prefix + fileName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filePath.toUri());
    }

    public void allocate(Map<StringType, List<String>> data){

    }
}
