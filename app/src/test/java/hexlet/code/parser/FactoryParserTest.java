package hexlet.code.parser;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FactoryParserTest {

    @Test
    void createParser() throws Exception {
        Path filepath;
        filepath = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();

        Parser actual;
        actual = FactoryParser.getParser(filepath, filepath);
        assertInstanceOf(JSONParser.class, actual);
        filepath = Paths.get("src/test/resources/file1.yml").toAbsolutePath().normalize();
        actual = FactoryParser.getParser(filepath, filepath);
        assertInstanceOf(YAMLParser.class, actual);
    }
}
