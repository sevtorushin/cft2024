package ru.cft.service;

import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileLineReader {

    private final String lineSeparator = System.lineSeparator();
    private static final Logger log = LogManager.getLogger(FileLineReader.class.getSimpleName());


    public List<String> getLinesOnStringsRange(@NonNull File file, int fromString, int stringCount) {
        List<String> result = new ArrayList<>();
        if (fromString < 1 || stringCount < 0) {
            log.warn(String.format("Wrong range:\n'fromString': %s\n'stringCount': %s", fromString, stringCount));
            return result;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            result = reader.lines().skip(fromString - 1).limit(stringCount).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            log.warn(String.format("File '%s' not found", file), e);
        } catch (IOException e) {
            log.error(String.format("File '%s' auto-close error", file), e);
        }
        return result;
    }

    @Deprecated
    public List<String> getLinesOnBytes(File file, int bytes) {
        List<String> result = new ArrayList<>();
        String s;
        int currentBytes = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (true) {
                s = reader.readLine();
                if (s == null || s.getBytes().length > bytes - currentBytes)
                    return result;
                result.add(s);
                currentBytes += s.getBytes().length;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Deprecated
    public List<String> getLinesOnStrings(File file, int stringCount) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            result = reader.lines().limit(stringCount).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Deprecated
    public List<String> getLinesOnByteRange(File file, int fromByte, int byteCount) {
        ByteBuffer tempBuffer = readFile(file, fromByte, byteCount);
        return toListStrings(tempBuffer);
    }

    private List<String> toListStrings(ByteBuffer tempBuffer) {
        List<String> result;
        int offset = tempBuffer.position();
        int length = tempBuffer.limit();
        byte[] bytes = new byte[length];
        tempBuffer.get(bytes, offset, length);
        result = new String(bytes).lines().collect(Collectors.toList());
        return result;
    }

    private ByteBuffer readFile(File file, int fromByte, int byteCount) {
        ByteBuffer tempBuffer = ByteBuffer.allocate(byteCount);
        try (FileChannel fileChannel = FileChannel.open(Path.of(file.toURI()), StandardOpenOption.READ)) {
            fileChannel.read(tempBuffer, fromByte);
            if (fileChannel.size() != byteCount)
                trimBuffer(tempBuffer);
            tempBuffer.flip();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempBuffer;
    }

    private void trimBuffer(ByteBuffer buffer) {
        byte[] ls = lineSeparator.getBytes();
        buffer.position(buffer.position() - 1);
        int position = buffer.position();
        while (buffer.position() != 0) {
            if (buffer.get(buffer.position()) == ls[ls.length - 1])
                break;
            position--;
            buffer.position(position);
        }
    }
}
