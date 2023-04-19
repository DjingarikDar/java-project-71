package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class YAMLParser implements Parser {
    @Override
    public Map<String, Object> toMap(Path filepath) throws IOException {
        if (Files.readString(filepath).isBlank()) {
            return new HashMap<>();
        }
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(Files.readString(filepath), new TypeReference<>() {
        });
    }
}
