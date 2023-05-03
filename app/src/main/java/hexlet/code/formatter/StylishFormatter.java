package hexlet.code.formatter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class StylishFormatter implements Formatter {

    @Override
    public String generateString(List<LinkedHashMap<String, Object>> list) {
        return list.stream()
                .map(this::mapToString)
                .collect(Collectors.joining("", "{\n", "}"));


    }

    private String mapToString(LinkedHashMap<String, Object> map) {
        StringBuilder builder = new StringBuilder();
        map.forEach((key, value) -> {
            if (key.startsWith("+") || key.startsWith("-")) {
                builder.append("  ");
            } else {
                builder.append("    ");
            }
            builder.append(key).append(": ").append(value).append("\n");
        });
        return builder.toString();
    }
}
