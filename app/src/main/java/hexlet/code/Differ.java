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
        Parser parser;
        if (filepath1.endsWith("json") && filepath2.endsWith("json")) {
            parser = FactoryParser.createParser(ParserType.JSON);
        } else if (filepath1.endsWith("yml") && filepath2.endsWith("yml")) {
            parser = FactoryParser.createParser(ParserType.YAML);
        } else {
            throw new Exception("files have different types \n"
                    + pathToFile1
                    + "\n"
                    + pathToFile2);
        }
        Map<String, Object> file1ToMap = parser.toMap(pathToFile1);
        Map<String, Object> file2ToMap = parser.toMap(pathToFile2);
        if (file1ToMap.isEmpty() && file2ToMap.isEmpty()) {
            return "{}";
        }
        Stream<String> streamKey = Stream.concat(file1ToMap.keySet().stream(), file2ToMap.keySet().stream());
        var keys = streamKey.collect(Collectors.toSet());
        return keys.stream()
                .sorted(String::compareTo)
                .map(key -> strResultOfComparingMaps(key, file1ToMap, file2ToMap))
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
