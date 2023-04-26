package hexlet.code;

import hexlet.code.parser.JSONParser;
import hexlet.code.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONParserTest {

    @Test
    void toMap() throws IOException {
        Path filepath;
        Map<String, Object> actual;
        Map<String, Object> expected = new HashMap<>();
        expected.put("setting1", "Some value");
        expected.put("setting2", 200);
        expected.put("setting3", true);
        expected.put("key1", "value1");
        expected.put("numbers1", List.of(1, 2, 3, 4));
        expected.put("numbers2", List.of(2, 3, 4, 5));
        expected.put("id", 45);
        expected.put("default", null);
        expected.put("checked", false);
        expected.put("numbers3", List.of(3, 4, 5));
        expected.put("chars1", List.of("a", "b", "c"));
        expected.put("chars2", List.of("d", "e", "f"));

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
