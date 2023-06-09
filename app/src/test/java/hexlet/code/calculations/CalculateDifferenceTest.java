package hexlet.code.calculations;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateDifferenceTest {
    private final Path pathToEmptyJsonFile = Paths.get("src/test/resources/empty.json").toAbsolutePath().normalize();
    private final Path pathToBlankJsonFile = Paths.get("src/test/resources/blank.json").toAbsolutePath().normalize();
    private final Path pathToBlankYamlFile = Paths.get("src/test/resources/blank.yml").toAbsolutePath().normalize();
    private final Path pathToJsonFile1 = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();
    private final Path pathToJsonFile2 = Paths.get("src/test/resources/file2.json").toAbsolutePath().normalize();
    private final Path pathToYamlFile1 = Paths.get("src/test/resources/file1.yml").toAbsolutePath().normalize();
    private final List<Integer> list1ForMapInit = List.of(1, 2, 3, 4);
    private final List<Integer> list2ForMapInit = List.of(2, 3, 4, 5);
    private final List<Integer> list3ForMapInit = List.of(22, 33, 44, 55);
    private final List<Integer> list4ForMapInit = List.of(3, 4, 5);
    private final List<Integer> list5ForMapInit = List.of(4, 5, 6);

    private final int value1ForMapInit = 45;
    private final int value2ForMapInit = 200;
    private final int value3ForMapInit = 300;




    @Test
    void generateEmptyFiles() throws Exception {
        List<Map<String, Object>> expected = new ArrayList<>();
        List<Map<String, Object>> actual;

        actual = CalculateDifference.generate(pathToEmptyJsonFile, pathToBlankJsonFile);
        assertEquals(expected, actual);

        actual = CalculateDifference.generate(pathToBlankYamlFile, pathToBlankYamlFile);
        assertEquals(expected, actual);
    }

    @Test
    void generate() throws Exception {
        List<Map<String, Object>> expected = new ArrayList<>();
        List<Map<String, Object>> actual;

        expected.add(Map.of(
                "fieldStatus", FieldStatus.UNCHANGED,
                "field", "chars1",
                "value", List.of("a", "b", "c")
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.CHANGED,
                "field", "chars2",
                "oldValue", List.of("d", "e", "f"),
                "newValue", false
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.CHANGED,
                "field", "checked",
                "oldValue", false,
                "newValue", true
        ));
        Map<String, Object> map = new HashMap<>();
        map.put("fieldStatus", FieldStatus.CHANGED);
        map.put("field", "default");
        map.put("oldValue", null);
        map.put("newValue", List.of("value1", "value2"));
        expected.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("fieldStatus", FieldStatus.CHANGED);
        map1.put("field", "id");
        map1.put("oldValue", value1ForMapInit);
        map1.put("newValue", null);
        expected.add(map1);
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "key1",
                "value", "value1"
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "key2",
                "value", "value2"
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.UNCHANGED,
                "field", "numbers1",
                "value", list1ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.CHANGED,
                "field", "numbers2",
                "oldValue", list2ForMapInit,
                "newValue", list3ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "numbers3",
                "value", list4ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "numbers4",
                "value", list5ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "obj1",
                "value", Map.of(
                        "nestedKey", "value",
                        "isNested", true
                )
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.CHANGED,
                "field", "setting1",
                "oldValue", "Some value",
                "newValue", "Another value"
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.CHANGED,
                "field", "setting2",
                "oldValue", value2ForMapInit,
                "newValue", value3ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.CHANGED,
                "field", "setting3",
                "oldValue", true,
                "newValue", "none"
        ));

        actual = CalculateDifference.generate(pathToJsonFile1, pathToJsonFile2);
        assertEquals(expected, actual);
    }

    @Test
    void generateLastFileEmpty() throws Exception {
        List<Map<String, Object>> expected = new ArrayList<>();
        List<Map<String, Object>> actual;
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "chars1",
                "value", List.of("a", "b", "c")
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "chars2",
                "value", List.of("d", "e", "f")
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "checked",
                "value", false
        ));
        Map<String, Object> map = new HashMap<>();
        map.put("fieldStatus", FieldStatus.REMOVED);
        map.put("field", "default");
        map.put("value", null);
        expected.add(map);
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "id",
                "value", value1ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "key1",
                "value", "value1"
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "numbers1",
                "value", list1ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "numbers2",
                "value", list2ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "numbers3",
                "value", list4ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "setting1",
                "value", "Some value"
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "setting2",
                "value", value2ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.REMOVED,
                "field", "setting3",
                "value", true
        ));

        actual = CalculateDifference.generate(pathToYamlFile1, pathToBlankYamlFile);
        assertEquals(expected, actual);
    }

    @Test
    void generateFirstFileEmpty() throws Exception {
        List<Map<String, Object>> expected = new ArrayList<>();
        List<Map<String, Object>> actual;
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "chars1",
                "value", List.of("a", "b", "c")
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "chars2",
                "value", List.of("d", "e", "f")
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "checked",
                "value", false
        ));
        Map<String, Object> map = new HashMap<>();
        map.put("fieldStatus", FieldStatus.ADDED);
        map.put("field", "default");
        map.put("value", null);
        expected.add(map);
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "id",
                "value", value1ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "key1",
                "value", "value1"
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "numbers1",
                "value", list1ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "numbers2",
                "value", list2ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "numbers3",
                "value", list4ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "setting1",
                "value", "Some value"
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "setting2",
                "value", value2ForMapInit
        ));
        expected.add(Map.of(
                "fieldStatus", FieldStatus.ADDED,
                "field", "setting3",
                "value", true
        ));

        actual = CalculateDifference.generate(pathToBlankYamlFile, pathToYamlFile1);
        assertEquals(expected, actual);
    }
}

