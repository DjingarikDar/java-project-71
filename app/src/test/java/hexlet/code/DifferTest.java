package hexlet.code;

import hexlet.code.calculations.CalculateDifference;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private final String strPathToJsonFile1 = "src/test/resources/file1.json";
    private final String strPathToJsonFile2 = "src/test/resources/file2.json";
    private final String strPathToYamlFile1 = "src/test/resources/file1.yml";
    private final String strPathToYamlFile2 = "src/test/resources/file2.yml";

    @Test
    void generateStylish() throws Exception {
        String format = "stylish";
        String actual = Differ.generate(strPathToJsonFile1, strPathToJsonFile2, format);
        Path path1 = Paths.get(strPathToJsonFile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(strPathToJsonFile2).toAbsolutePath().normalize();

        StylishFormatter formatter = new StylishFormatter();

        String expected = formatter
                .generateString(CalculateDifference.generate(path1, path2));
        assertEquals(expected, actual);
    }

    @Test
    void generatePlain() throws Exception {
        String format = "plain";
        String actual = Differ.generate(strPathToYamlFile1, strPathToYamlFile2, format);
        Path path1 = Paths.get(strPathToYamlFile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(strPathToYamlFile2).toAbsolutePath().normalize();

        PlainFormatter formatter = new PlainFormatter();
        String expected = formatter
                .generateString(CalculateDifference.generate(path1, path2));
        assertEquals(expected, actual);
    }

    @Test
    void generateJson() throws Exception {
        String format = "json";
        String actual = Differ.generate(strPathToJsonFile1, strPathToJsonFile2, format);
        Path path1 = Paths.get(strPathToJsonFile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(strPathToJsonFile2).toAbsolutePath().normalize();

        JsonFormatter formatter = new JsonFormatter();
        String expected = formatter
                .generateString(CalculateDifference.generate(path1, path2));
        assertEquals(expected, actual);
    }
}
