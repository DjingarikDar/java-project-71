package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public final class JsonFormatter implements Formatter {
    @Override
    public String generateString(List<Map<String, Object>> list) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        try {
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
