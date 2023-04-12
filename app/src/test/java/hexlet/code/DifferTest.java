package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    String pathToEmptyJsonFile = "src/test/resources/empty.json";
    String pathToBlankJsonFile = "src/test/resources/blank.json";
    String pathToJsonFile1 = "src/test/resources/file1.json";
    String pathToJsonFile2 = "src/test/resources/file2.json";

    @Test
    public void differBlankEmptyTest() throws Exception {
        String expected;
        String actual;
        expected = "{}";
        actual = Differ.generator(pathToEmptyJsonFile, pathToEmptyJsonFile);
        assertEquals(expected, actual);
        actual = Differ.generator(pathToBlankJsonFile, pathToBlankJsonFile);
        assertEquals(expected, actual);
        expected = """
                {
                  + Double: 41.2
                  + a-key: a-value
                  + boo: far
                  + follow: false
                  + host: hexlet.io
                  + proxy: 123.234.53.22
                  + timeout: 50
                }""";
        actual = Differ.generator(pathToBlankJsonFile, pathToJsonFile1);
        assertEquals(expected, actual);
        expected = """
                {
                  - Double: 34.1
                  - b-key: b-value
                  - boo: far
                  - host: hexlet.io
                  - timeout: 20
                  - verbose: true
                }""";
        actual = Differ.generator(pathToJsonFile2, pathToEmptyJsonFile);
        assertEquals(expected, actual);
    }
    @Test
    public void differTest() throws Exception {
        String expected;
        String actual;
        actual = Differ.generator(pathToJsonFile1, pathToJsonFile2);
        expected = """
                {
                  - Double: 41.2
                  + Double: 34.1
                  - a-key: a-value
                  + b-key: b-value
                    boo: far
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(expected, actual);
    }
}
