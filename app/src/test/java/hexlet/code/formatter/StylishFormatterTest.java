package hexlet.code.formatter;

import hexlet.code.calculations.CalculateDifference;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {
    private final Path pathToFile1 = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();
    private final Path pathToFile2 = Paths.get("src/test/resources/file2.json").toAbsolutePath().normalize();
    @Test
    void generateString() throws Exception {

        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        StylishFormatter stylishFormatter = new StylishFormatter();
        List<Map<String, Object>> list = CalculateDifference.generate(pathToFile1, pathToFile2);
        String actual = stylishFormatter.generateString(list);
        assertEquals(expected, actual);
    }
}
