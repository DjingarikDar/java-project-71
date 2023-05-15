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
    Path pathToJsonFile1 = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();
    Path pathToJsonFile2 = Paths.get("src/test/resources/file2.json").toAbsolutePath().normalize();
    Path pathToYamlFile1 = Paths.get("src/test/resources/file1.yml").toAbsolutePath().normalize();
    Path pathToYamlFile2 = Paths.get("src/test/resources/file2.yml").toAbsolutePath().normalize();


    @Test
    void generateStylish() throws Exception {
        String format = "stylish";
        String actual = Differ.generate(pathToJsonFile1, pathToJsonFile2, format);
        StylishFormatter formatter = new StylishFormatter();
        String expected = formatter
                .generateString(CalculateDifference.generate(pathToJsonFile1, pathToJsonFile2));
        assertEquals(expected, actual);
    }
    @Test
    void generatePlain() throws Exception {
        String format = "plain";
        String actual = Differ.generate(pathToYamlFile1, pathToYamlFile2, format);
        PlainFormatter formatter = new PlainFormatter();
        String expected = formatter
                .generateString(CalculateDifference.generate(pathToYamlFile1, pathToYamlFile2));
        assertEquals(expected, actual);
    }
    @Test
    void generateJson() throws Exception {
        String format = "json";
        String actual = Differ.generate(pathToJsonFile1, pathToJsonFile2, format);
        JsonFormatter formatter = new JsonFormatter();
        String expected = formatter
                .generateString(CalculateDifference.generate(pathToJsonFile1, pathToJsonFile2));
        assertEquals(expected, actual);
    }
}
