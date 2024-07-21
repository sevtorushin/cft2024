package ru.cft;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileManager {

    private final String prefix;
    private final String path;
    @Getter
    @Setter
    private boolean appendMode;
    private final String fileExtension;

    private final FileLineWriter writer;

    public FileManager(String prefix, String path, boolean appendMode, String fileExtension) {
        this.prefix = prefix;
        this.path = path;
        this.appendMode = appendMode;
        this.fileExtension = fileExtension;
        this.writer = new FileLineWriter();
        prepareFiles();
    }

    private File createFile(String fileName) {
        if (fileName.matches(".\\.*"))
            throw new IllegalArgumentException("Invalid file name");
        Path filePath = Path.of(path, prefix + fileName + "." + fileExtension);
        try {
            if (!Files.exists(filePath))
                Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filePath.toUri());
    }

    public void allocate(Map<StringType, List<String>> data) {
        data.forEach((key, value) -> {
            if (!value.isEmpty()) {
                File file = createFile(key.name().toLowerCase() + "s");
                writer.writeLines(file, value, true);
            }
        });
    }

    private void prepareFiles() {
        if (!appendMode) {
            List<String> fileNamesList = Arrays.stream(StringType.values())
                    .map(stringType -> prefix + stringType.name().toLowerCase() + "s." + fileExtension).collect(Collectors.toList());
            Path root = Path.of(path);
            try {
                Files.list(root)
                        .map(path1 -> path1.getFileName().toString())
                        .filter(fileNamesList::contains)
                        .map(s -> Path.of(path, s))
                        .forEach(p -> {
                            try {
                                if (Files.exists(p))
                                    Files.delete(p);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
