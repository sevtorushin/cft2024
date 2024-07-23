package ru.cft.service;

import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.AccessDeniedException;
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
        } catch (AccessDeniedException e) {
            log.warn("123");
        } catch (FileNotFoundException e){
            log.warn(String.format("File '%s' - access denied. The file will not be written.", file));
        }
        catch (IOException e) {
            log.error(String.format("File '%s' auto-close error", file), e);
        }
    }
}
