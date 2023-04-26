package hexlet.code;

import hexlet.code.parser.TypeFile;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeFileTest {

    @Test
    void get() throws Exception {
        Path filepath;
        String actual;
        String expected;
        filepath = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();
        actual = TypeFile.get(filepath);
        expected = "json";
        assertEquals(actual, expected);
        filepath = Paths.get("src/test/resources/file1.yml").toAbsolutePath().normalize();
        actual = TypeFile.get(filepath);
        expected = "yml";
        assertEquals(expected, actual);
    }
}
