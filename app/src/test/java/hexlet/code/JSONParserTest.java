package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONParserTest {

    @Test
    void toMap() throws IOException {
        Path filepath;
        Map<String, Object> actual;
        Map<String, Object> expected = Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false,
                "a-key", "a-value",
                "Double", 41.2,
                "boo", "far"
        );
        Parser parser = new JSONParser();
        filepath = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();
        actual = parser.toMap(filepath);
        assertEquals(expected, actual);
        filepath = Paths.get("src/test/resources/blank.json").toAbsolutePath().normalize();
        Map<String, Object> expected2 = Map.of();
        actual.clear();
        actual.putAll(parser.toMap(filepath));
        assertEquals(expected2, actual);
        filepath = Paths.get("src/test/resources/empty.json").toAbsolutePath().normalize();
        actual.clear();
        actual.putAll(parser.toMap(filepath));
        assertEquals(expected2, actual);
    }
}
