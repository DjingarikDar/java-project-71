package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> jsonToMap(Path pathToJsonFile) throws Exception {
        if (Files.readString(pathToJsonFile).isBlank()) {
            return new HashMap<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Files.readString(pathToJsonFile), new TypeReference<>() {
        });
    }
}
