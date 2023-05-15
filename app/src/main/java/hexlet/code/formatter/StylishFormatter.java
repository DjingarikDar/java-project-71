package hexlet.code.formatter;

import hexlet.code.calculations.FieldStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StylishFormatter implements Formatter {

    @Override
    public String generateString(List<Map<String, Object>> list) {
        return list.stream()
                .map(this::mapToString)
                .collect(Collectors.joining("\n", "{\n", "\n}"));


    }

    private String mapToString(Map<String, Object> map) {

        FieldStatus fieldStatus = (FieldStatus) map.get("fieldStatus");
        switch (fieldStatus) {
            case UNCHANGED -> {
                return "    %s: %s".formatted(map.get("field"), map.get("value"));
            }
            case CHANGED -> {
                return "  - %s: %s\n  + %s: %s"
                        .formatted(map.get("field"), map.get("oldValue"), map.get("field"), map.get("newValue"));
            }
            case ADDED -> {
                return "  + %s: %s".formatted(map.get("field"), map.get("value"));
            }
            case REMOVED -> {
                return  "  - %s: %s".formatted(map.get("field"), map.get("value"));
            }
            default -> throw new IllegalStateException("Unexpected fieldStatus: " + fieldStatus);
        }

    }
}
