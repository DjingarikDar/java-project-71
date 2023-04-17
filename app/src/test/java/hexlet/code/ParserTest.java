package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    String pathToEmptyJsonFile = "src/test/resources/empty.json";
    String pathToBlankJsonFile = "src/test/resources/blank.json";
    String pathToJsonFile1 = "src/test/resources/file1.json";

    @Test
    void blankJsonToMap() throws Exception {
        Map<String, Object> expected = new HashMap<>();
        Map<String, Object> actual = Parser.jsonToMap(Paths.get(pathToBlankJsonFile).toAbsolutePath().normalize());
        assertEquals(expected, actual);
        Path pathToEmptyJson = Paths.get(pathToEmptyJsonFile).toAbsolutePath().normalize();
        actual.clear();
        actual.putAll(Parser.jsonToMap(pathToEmptyJson));
        assertEquals(expected, actual);
    }

    @Test
    void jsonToMap() throws Exception {
        Map<String, Object> expected = Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false,
                "a-key", "a-value",
                "Double", 41.2,
                "boo", "far"
        );
        Map<String, Object> actual = Parser.jsonToMap(Paths.get(pathToJsonFile1).toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
}
