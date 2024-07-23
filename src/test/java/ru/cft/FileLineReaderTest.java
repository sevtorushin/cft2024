package ru.cft;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.cft.service.FileLineReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

class FileLineReaderTest {

    private static FileLineReader reader;
    private static File file;

    @BeforeAll
    static void beforeAll() {
        reader = new FileLineReader();
        file = new File("test.txt");
        try (FileWriter writer = new FileWriter(file)){
            writer.write("1\n2\n3");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Stream<Arguments> methodArgsProvider() {
        return Stream.of(
                Arguments.arguments(file, 1, 1),
                Arguments.arguments(null, 1 ,1),
                Arguments.arguments((Object) null)
        );
    }

    @ParameterizedTest
    @MethodSource("methodArgsProvider")
    void getLinesOnStringsRange(File file, int from, int amount) {
        List<String> lines = reader.getLinesOnStringsRange(file, from, amount);
    }
}