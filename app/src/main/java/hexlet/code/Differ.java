package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Differ {
    public static String generator(String filepath1, String filepath2) throws Exception {
        Path pathToFile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path pathToFile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, Object> mapJsonFile1 = Parser.jsonToMap(pathToFile1);
        Map<String, Object> mapJsonFile2 = Parser.jsonToMap(pathToFile2);
        if (mapJsonFile1.isEmpty() && mapJsonFile2.isEmpty()) {
            return "{}";
        }
        Stream<String> streamKey = Stream.concat(mapJsonFile1.keySet().stream(), mapJsonFile2.keySet().stream());
        var keys = streamKey.collect(Collectors.toSet());
        return keys.stream()
                .sorted(String::compareTo)
                .map(key -> strResultOfComparingMaps(key, mapJsonFile1, mapJsonFile2))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String strResultOfComparingMaps(String key, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder resultStr = new StringBuilder();
        if (map1.containsKey(key) && map2.containsKey(key)) {
            if (map1.get(key).equals(map2.get(key))) {
                resultStr.append("    ").append(key).append(": ").append(map1.get(key));
            } else {
                resultStr.append("  - ").append(key)
                        .append(": ").append(map1.get(key)).append("\n")
                        .append("  + ").append(key).append(": ").append(map2.get(key));
            }
        } else if (map1.containsKey(key)) {
            resultStr.append("  - ").append(key).append(": ").append(map1.get(key));
        } else {
            resultStr.append("  + ").append(key).append(": ").append(map2.get(key));
        }
        return resultStr.toString();
    }

}
