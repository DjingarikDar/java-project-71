package hexlet.code.formatter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class PlainFormatter implements Formatter {
    @Override
    public String generateString(List<LinkedHashMap<String, Object>> list) {
        return list.stream()
                .map(this::mapToString)
                .filter(item -> !item.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mapToString(LinkedHashMap<String, Object> map) {
        map.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof String)
                .forEach(entry -> entry.setValue("'" + entry.getValue() + "'"));
        map.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Collection<?>
                                 || entry.getValue() instanceof Map<?, ?>)
                .forEach(entry -> entry.setValue("[complex value]"));
        if (map.size() > 1) {
            return generateUpdateString(map);
        } else {
            return generateAddRemoveString(map);
        }
    }

    private String generateAddRemoveString(LinkedHashMap<String, Object> map) {
        StringBuilder resultString = new StringBuilder();
        String key = map.keySet().stream().findFirst().get();

        if (key.startsWith("+")) {
            resultString.append("Property '").append(key.split(" ")[1]).append("' was added with value: ")
                    .append(map.get(key));
        } else if (key.startsWith("-")) {
            resultString.append("Property '").append(key.split(" ")[1]).append("' was removed");
        }
        return resultString.toString();
    }

    private String generateUpdateString(LinkedHashMap<String, Object> map) {
        StringBuilder resultString = new StringBuilder();
        String key = map.keySet().stream().findFirst().get().split(" ")[1];
        resultString.append("Property '").append(key).append("' was updated. ");
        var values = map.values().toArray();
        resultString.append("From ").append(values[0]).append(" to ").append(values[1]);

        return resultString.toString();
    }

}
