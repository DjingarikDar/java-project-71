package hexlet.code.formatter;

import hexlet.code.calculations.CalculateDifference;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFormatterTest {
    Path pathToFile1 = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();
    Path pathToFile2 = Paths.get("src/test/resources/file2.json").toAbsolutePath().normalize();

  

    @Test
    void generateString() throws Exception {
        String expected = "[{\"field\":\"chars1\",\"fieldStatus\":\"UNCHANGED\",\"value\":[\"a\",\"b\",\"c\"]},"
                + "{\"field\":\"chars2\",\"fieldStatus\":\"CHANGED\","
                + "\"newValue\":false,\"oldValue\":[\"d\",\"e\",\"f\"]},"
                + "{\"field\":\"checked\",\"fieldStatus\":\"CHANGED\","
                + "\"newValue\":true,\"oldValue\":false},"
                + "{\"field\":\"default\",\"fieldStatus\":\"CHANGED\","
                + "\"newValue\":[\"value1\",\"value2\"],\"oldValue\":null},"
                + "{\"field\":\"id\",\"fieldStatus\":\"CHANGED\",\"newValue\":null,\"oldValue\":45},"
                + "{\"field\":\"key1\",\"fieldStatus\":\"REMOVED\",\"value\":\"value1\"},"
                + "{\"field\":\"key2\",\"fieldStatus\":\"ADDED\",\"value\":\"value2\"},"
                + "{\"field\":\"numbers1\",\"fieldStatus\":\"UNCHANGED\",\"value\":[1,2,3,4]},"
                + "{\"field\":\"numbers2\",\"fieldStatus\":\"CHANGED\","
                + "\"newValue\":[22,33,44,55],\"oldValue\":[2,3,4,5]},"
                + "{\"field\":\"numbers3\",\"fieldStatus\":\"REMOVED\",\"value\":[3,4,5]},"
                + "{\"field\":\"numbers4\",\"fieldStatus\":\"ADDED\",\"value\":[4,5,6]},"
                + "{\"field\":\"obj1\",\"fieldStatus\":\"ADDED\","
                + "\"value\":{\"isNested\":true,\"nestedKey\":\"value\"}},"
                + "{\"field\":\"setting1\",\"fieldStatus\":\"CHANGED\","
                + "\"newValue\":\"Another value\",\"oldValue\":\"Some value\"},"
                + "{\"field\":\"setting2\",\"fieldStatus\":\"CHANGED\",\"newValue\":300,\"oldValue\":200},"
                + "{\"field\":\"setting3\",\"fieldStatus\":\"CHANGED\",\"newValue\":\"none\",\"oldValue\":true}]";
        JsonFormatter formatter = new JsonFormatter();
        List<Map<String, Object>> list = CalculateDifference.generate(pathToFile1, pathToFile2);
        String actual = formatter.generateString(list);
        assertEquals(expected, actual);
    }

}
