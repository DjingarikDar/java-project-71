package hexlet.code.formatter;

import java.util.List;
import java.util.Map;


public interface Formatter {
    String generateString(List<Map<String, Object>> list);
}
