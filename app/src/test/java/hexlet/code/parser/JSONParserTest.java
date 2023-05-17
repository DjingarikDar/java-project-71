package hexlet.code.parser;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONParserTest {
    private final List<Integer> list1ForMapInit = List.of(1, 2, 3, 4);
    private final List<Integer> list2ForMapInit = List.of(2, 3, 4, 5);
    private final List<Integer> list3ForMapInit = List.of(3, 4, 5);

    private final int value1ForMapInit = 45;
    private final int value2ForMapInit = 200;

    @Test
    void toMap() throws IOException {
        Path filepath;
        Map<String, Object> actual;
        Map<String, Object> expected = new HashMap<>();
        expected.put("setting1", "Some value");
        expected.put("setting2", value2ForMapInit);
        expected.put("setting3", true);
        expected.put("key1", "value1");
        expected.put("numbers1", list1ForMapInit);
        expected.put("numbers2", list2ForMapInit);
        expected.put("id", value1ForMapInit);
        expected.put("default", null);
        expected.put("checked", false);
        expected.put("numbers3", list3ForMapInit);
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
