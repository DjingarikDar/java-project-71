package hexlet.code.formatter;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainFormatterTest {

    String pathToJsonFile1 = "src/test/resources/file1.json";
    String pathToJsonFile2 = "src/test/resources/file2.json";


    @Test
    void generateString() throws Exception {

        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        Differ differ = new Differ(pathToJsonFile1, pathToJsonFile2, "plain");
        String actual = differ.generateString();
        assertEquals(expected, actual);
    }
}
