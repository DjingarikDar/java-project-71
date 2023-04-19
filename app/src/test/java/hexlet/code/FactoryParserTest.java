package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FactoryParserTest {

    @Test
    void createParser() {
        Parser actual;
        actual = FactoryParser.createParser(ParserType.JSON);
        assertInstanceOf(JSONParser.class, actual);
        actual = FactoryParser.createParser(ParserType.YAML);
        assertInstanceOf(YAMLParser.class, actual);
    }
}
