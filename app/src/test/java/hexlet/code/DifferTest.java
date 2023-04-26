package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    String pathToEmptyJsonFile = "src/test/resources/empty.json";
    String pathToBlankJsonFile = "src/test/resources/blank.json";
    String pathToBlankYamlFile = "src/test/resources/blank.yml";
    String pathToJsonFile1 = "src/test/resources/file1.json";
    String pathToJsonFile2 = "src/test/resources/file2.json";
    String pathToYamlFile1 = "src/test/resources/file1.yml";
    String pathToYamlFile2 = "src/test/resources/file2.yml";

    @Test
    public void differJSONBlankTest() throws Exception {
        ArrayList<Object> expected = new ArrayList<>();
        List<LinkedHashMap<String, Object>> actual;
        String format = "stylish";
        Differ differ1 = new Differ(pathToEmptyJsonFile, pathToEmptyJsonFile, format);

        actual = differ1.generateDiff();
        assertEquals(expected, actual);

        Differ differ2 = new Differ(pathToBlankJsonFile, pathToBlankJsonFile, format);
        actual.clear();
        actual.addAll(differ2.generateDiff());
        assertEquals(expected, actual);

        expected.clear();
        expected.add(Map.of("+ chars1", List.of("a", "b", "c")));
        expected.add(Map.of("+ chars2", List.of("d", "e", "f")));
        expected.add(Map.of("+ checked", false));
        Map<String, Object> e = new HashMap<>();
        e.put("+ default", null);
        expected.add(e);
        expected.add(Map.of("+ id", 45));
        expected.add(Map.of("+ key1", "value1"));
        expected.add(Map.of("+ numbers1", List.of(1, 2, 3, 4)));
        expected.add(Map.of("+ numbers2", List.of(2, 3, 4, 5)));
        expected.add(Map.of("+ numbers3", List.of(3, 4, 5)));
        expected.add(Map.of("+ setting1", "Some value"));
        expected.add(Map.of("+ setting2", 200));
        expected.add(Map.of("+ setting3", true));

        Differ differ3 = new Differ(pathToBlankJsonFile, pathToJsonFile1, format);
        actual.clear();
        actual.addAll(differ3.generateDiff());
        assertEquals(expected, actual);

        expected.clear();
        expected.add(Map.of("- chars1", List.of("a", "b", "c")));
        expected.add(Map.of("- chars2", false));
        expected.add(Map.of("- checked", true));
        expected.add(Map.of("- default", List.of("value1", "value2")));
        Map<String, Object> e1 = new HashMap<>();
        e1.put("- id", null);
        expected.add(e1);
        expected.add(Map.of("- key2", "value2"));
        expected.add(Map.of("- numbers1", List.of(1, 2, 3, 4)));
        expected.add(Map.of("- numbers2", List.of(22, 33, 44, 55)));
        expected.add(Map.of("- numbers4", List.of(4, 5, 6)));
        expected.add(Map.of("- obj1", Map.of(
                "nestedKey", "value",
                "isNested", true
        )));
        expected.add(Map.of("- setting1", "Another value"));
        expected.add(Map.of("- setting2", 300));
        expected.add(Map.of("- setting3", "none"));

        Differ differ4 = new Differ(pathToJsonFile2, pathToEmptyJsonFile, format);
        actual.clear();
        actual.addAll(differ4.generateDiff());
        assertEquals(expected, actual);
    }

//    @Test
//    public void differYAMLBlankTest() throws Exception {
//        String format = "stylish";
//        String expected;
//        String actual;
//        expected = "{}";
//        actual = Differ.generator(pathToBlankYamlFile, pathToBlankYamlFile, format);
//        assertEquals(expected, actual);
//        expected = """
//                {
//                  + Double: 41.2
//                  + a-key: a-value
//                  + boo: far
//                  + follow: false
//                  + host: hexlet.io
//                  + proxy: 123.234.53.22
//                  + timeout: 50
//                }""";
//        actual = Differ.generator(pathToBlankYamlFile, pathToYamlFile1, format);
//        assertEquals(expected, actual);
//        expected = """
//                {
//                  - Double: 34.1
//                  - b-key: b-value
//                  - boo: far
//                  - host: hexlet.io
//                  - timeout: 20
//                  - verbose: true
//                }""";
//        actual = Differ.generator(pathToYamlFile2, pathToBlankYamlFile, format);
//        assertEquals(expected, actual);
//    }

    @Test
    public void differJSONTest() throws Exception {
        String format = "stylish";
        List<Object> expected;
        Map<String, List<String>> elements = new java.util.HashMap<>();
        elements.put("- default", null);
        elements.put("+ default", List.of("value1", "value2"));
        Map<String, Integer> elements1 = new java.util.HashMap<>();
        elements1.put("- id", 45);
        elements1.put("+ id", null);
        expected = List.of(
                Map.of("chars1", List.of("a", "b", "c")),
                Map.of(
                        "- chars2", List.of("d", "e", "f"),
                        "+ chars2", false
                ),
                Map.of(
                        "- checked", false,
                        "+ checked", true
                ),
                elements,
                elements1,
                Map.of("- key1", "value1"),
                Map.of("+ key2", "value2"),
                Map.of("numbers1", List.of(1, 2, 3, 4)),
                Map.of(
                        "- numbers2", List.of(2, 3, 4, 5),
                        "+ numbers2", List.of(22, 33, 44, 55)
                ),
                Map.of("- numbers3", List.of(3, 4, 5)),
                Map.of("+ numbers4", List.of(4, 5, 6)),
                Map.of("+ obj1", Map.of(
                        "nestedKey", "value",
                        "isNested", true
                )),
                Map.of(
                        "- setting1", "Some value",
                        "+ setting1", "Another value"
                ),
                Map.of(
                        "- setting2", 200,
                        "+ setting2", 300
                ),
                Map.of(
                        "- setting3", true,
                        "+ setting3", "none"
                )
        );
        Differ differ = new Differ(pathToJsonFile1, pathToJsonFile2, format);
        List<LinkedHashMap<String, Object>> actual = differ.generateDiff();
        assertEquals(expected, actual);
    }

//    @Test
//    public void differYAMLTest() throws Exception {
//        String format = "stylish";
//        String expected;
//        String actual;
//        actual = Differ.generator(pathToYamlFile1, pathToYamlFile2, format);
//        expected = """
//                {
//                  - Double: 41.2
//                  + Double: 34.1
//                  - a-key: a-value
//                  + b-key: b-value
//                    boo: far
//                  - follow: false
//                    host: hexlet.io
//                  - proxy: 123.234.53.22
//                  - timeout: 50
//                  + timeout: 20
//                  + verbose: true
//                }""";
//        assertEquals(expected, actual);
//    }
}
