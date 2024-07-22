package ru.cft;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileManager {

    @NonNull
    private final String prefix;
    @NonNull
    private String path;
    @Getter
    @Setter
    private boolean appendMode;
    @NonNull
    private final String fileExtension;

    private final FileLineWriter writer;

    private static final Logger log = LogManager.getLogger(FileManager.class.getSimpleName());

    public FileManager(String prefix, String path, boolean appendMode, String fileExtension) {
        if (!isValidPrefix(prefix))
            this.prefix = "";
        else this.prefix = prefix;
        if (!isValidPath(path)) {
            this.path = "";
            log.warn("Output path invalid. Files will be saved in the root path");
        } else this.path = path;
        this.appendMode = appendMode;
        if (!isValidFileExtension(fileExtension))
            this.fileExtension = "txt";
        else
            this.fileExtension = fileExtension;
        this.writer = new FileLineWriter();
        prepareDirectory();
        prepareFiles();
    }

    private File createFile(String fileName) {
        if (fileName.contains("."))
            throw new IllegalArgumentException("Invalid file name");
        Path filePath = Path.of(path, prefix + fileName + "." + fileExtension);
        try {
            if (!Files.exists(filePath))
                Files.createFile(filePath);
        } catch (IOException e) {
            if (e instanceof AccessDeniedException) {
                log.warn(String.format("'%s' access denied. Files will be saved in the root path", path));
            }
            this.path = "";
            filePath = Path.of(path, prefix + fileName + "." + fileExtension);
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
                                log.error(e);
                            }
                        });
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    private void prepareDirectory() {
        Path p = Path.of(path);
        if (!Files.exists(p))
            try {
                Files.createDirectories(p);
            } catch (IOException e) {
                if (e instanceof AccessDeniedException) {
                    log.warn(String.format("'%s' access denied. Files will be saved in the root path", path));
                } else {
                    log.error(e);
                }
                this.path = "";
            }
    }

    private boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    private boolean isValidFileExtension(String fileExtension) {
        return fileExtension.matches("(\\w|\\d)+");
    }

    private boolean isValidPrefix(String prefix) {
        return prefix.matches("[^.*<>?+:/|\"\\s\\\\]+");
    }
}
