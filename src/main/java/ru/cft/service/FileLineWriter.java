package ru.cft.service;

import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class FileLineWriter {

    private static final Logger log = LogManager.getLogger(FileLineWriter.class.getSimpleName());

    public void writeLines(@NonNull File file, @NonNull List<String> lines, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
            for (String s : lines) {
                writer.write(s);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            log.error(String.format("File '%s' auto-close error", file), e);
        }
    }
}
