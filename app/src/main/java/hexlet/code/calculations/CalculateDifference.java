package hexlet.code.calculations;

import hexlet.code.parser.FactoryParser;
import hexlet.code.parser.Parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculateDifference {

    public static List<Map<String, Object>> generate(Path pathToFile1,
                                                         Path pathToFile2) throws Exception {
        Parser parser = FactoryParser.getParser(pathToFile1, pathToFile2);
        Map<String, Object> mapFromFile1 = parser.toMap(pathToFile1);
        Map<String, Object> mapFromFile2 = parser.toMap(pathToFile2);
        if (mapFromFile1.isEmpty() && mapFromFile2.isEmpty()) {
            return new ArrayList<>();
        }
        Stream<String> streamKey = Stream.concat(mapFromFile1.keySet().stream(), mapFromFile2.keySet().stream());
        var keys = streamKey.collect(Collectors.toSet());
        return keys.stream()
                .sorted(String::compareTo)
                .map(key -> getMapDifference(key, mapFromFile1, mapFromFile2))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Map<String, Object> getMapDifference(String key,
                                                        Map<String, Object> map1,
                                                        Map<String, Object> map2) {
        Map<String, Object> result = new HashMap<>();
        FieldStatus fieldStatus = getFieldStatus(key, map1, map2);
        result.put("fieldStatus", fieldStatus);
        result.put("field", key);
        switch (fieldStatus) {
            case ADDED -> result.put("value", map2.get(key));
            case REMOVED, UNCHANGED -> result.put("value", map1.get(key));
            case CHANGED -> {
                result.put("oldValue", map1.get(key));
                result.put("newValue", map2.get(key));
            }
            default -> throw new IllegalStateException("Unexpected FieldStatus: " + fieldStatus);
        }
        return result;
    }

    private static FieldStatus getFieldStatus(String key, Map<String, Object> map1, Map<String, Object> map2) {
        FieldStatus fieldStatus;
        if (!map1.containsKey(key)) {
            fieldStatus = FieldStatus.ADDED;
        } else if (!map2.containsKey(key)) {
            fieldStatus = FieldStatus.REMOVED;
        } else {
            fieldStatus = (Objects.equals(map1.get(key), map2.get(key))) ? FieldStatus.UNCHANGED : FieldStatus.CHANGED;
        }
        return fieldStatus;
    }
}
