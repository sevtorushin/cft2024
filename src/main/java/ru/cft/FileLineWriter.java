package ru.cft;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileLineWriter {

    public void writeLines(File file, List<String> lines){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(String s : lines) {
                writer.write(s);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendLines(File file, List<String> lines){
        try (FileWriter writer = new FileWriter(file, true)){
            for(String s : lines) {
                writer.write(System.lineSeparator());
                writer.write(s);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
