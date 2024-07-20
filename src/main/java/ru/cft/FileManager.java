package ru.cft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileManager {

//    private String integerFileName = "integers";
//    private String floatFileName = "floats";
//    private String stringFileName = "strings";
    private final String prefix;
    private final String path;

    private final FileLineWriter writer;

    public FileManager(String prefix, String path) {
        this.prefix = prefix;
        this.path = path;
        this.writer = new FileLineWriter();
    }

    private File createFile(String fileName, String prefix, String path){
        if (fileName.contains("."))
            throw new IllegalArgumentException("Invalid file name");
        Path filePath = null;
        try {
            filePath = Files.createFile(Path.of(path, prefix + fileName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filePath.toUri());
    }

    public void allocate(Map<StringType, List<String>> data){
        data.entrySet().forEach(entry -> {
            File file = createFile(entry.getKey().name().toLowerCase() + "s", prefix, path);
            writer.writeLines(file, entry.getValue(), true);
        });
    }
}
