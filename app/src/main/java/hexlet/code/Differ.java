package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generator(String filepath1, String filepath2) throws Exception {
        Path pathToFile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path pathToFile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mapJsonFile1 = mapper.readValue(Files.readString(pathToFile1), new TypeReference<>() {
        });
        Map<String, Object> mapJsonFile2 = mapper.readValue(Files.readString(pathToFile2), new TypeReference<>() {
        });
        Stream<String> streamKey = Stream.concat(mapJsonFile1.keySet().stream(), mapJsonFile2.keySet().stream());
        var keys = streamKey.collect(Collectors.toSet());
        return keys.stream()
                .sorted(String::compareTo)
                .map(key -> strResultOfComparingMaps(key, mapJsonFile1, mapJsonFile2))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String strResultOfComparingMaps(String key, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder resultStr = new StringBuilder();
        if (map1.containsKey(key) && !map2.containsKey(key)) {
            resultStr.append("  - ")
                    .append(key)
                    .append(":")
                    .append(" ")
                    .append(map1.get(key));
        }
        if (map2.containsKey(key) && !map1.containsKey(key)) {
            resultStr.append("  + ")
                    .append(key)
                    .append(": ")
                    .append(map2.get(key));
        }
        if (map1.containsKey(key) && map2.containsKey(key)) {
            if (map1.get(key).equals(map2.get(key))) {
                resultStr.append("    ")
                        .append(key)
                        .append(": ")
                        .append(map1.get(key));
            } else {
                resultStr.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(map1.get(key))
                        .append("\n")
                        .append("  + ")
                        .append(key)
                        .append(": ")
                        .append(map2.get(key));
            }
        }
        return resultStr.toString();
    }
}
