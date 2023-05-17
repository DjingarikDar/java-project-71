package hexlet.code.formatter;

import hexlet.code.calculations.FieldStatus;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class PlainFormatter implements Formatter {
    @Override
    public String generateString(List<Map<String, Object>> list) {
        return list.stream()
                .map(this::mapToString)
                .filter(item -> !item.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mapToString(Map<String, Object> map) {

        map.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof String)
                .forEach(entry -> entry.setValue("'" + entry.getValue() + "'"));
        map.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Collection<?>
                        || entry.getValue() instanceof Map<?, ?>)
                .forEach(entry -> entry.setValue("[complex value]"));
        FieldStatus fieldStatus = (FieldStatus) map.get("fieldStatus");
        switch (fieldStatus) {
            case UNCHANGED -> {
                return "";
            }
            case CHANGED -> {
                return "Property %s was updated. From %s to %s"
                    .formatted(map.get("field"), map.get("oldValue"), map.get("newValue"));
            }
            case REMOVED -> {
                return "Property %s was removed".formatted(map.get("field"));
            }
            case ADDED -> {
                return "Property %s was added with value: %s"
                        .formatted(map.get("field"), map.get("value"));
            }
            default -> throw new IllegalStateException("Unexpected fieldStatus: " + fieldStatus);
        }
    }
}
