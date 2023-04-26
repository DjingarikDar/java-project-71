package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JSONParser implements Parser {

    @Override
    public Map<String, Object> toMap(Path filepath) throws IOException {
        if (Files.readString(filepath).isBlank()) {
            return new HashMap<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Files.readString(filepath), new TypeReference<>() {
        });
    }
}
