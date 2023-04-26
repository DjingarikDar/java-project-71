package hexlet.code.formatter;

import java.util.LinkedHashMap;
import java.util.List;


public interface Formatter {
    String generateString(List<LinkedHashMap<String, Object>> list);
}
