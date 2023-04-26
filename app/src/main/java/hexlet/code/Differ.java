package hexlet.code;

import hexlet.code.formatter.FactoryFormatter;
import hexlet.code.formatter.Formatter;
import hexlet.code.parser.FactoryParser;
import hexlet.code.parser.Parser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Differ {
    private final Path pathToFile1;
    private final Path pathToFile2;


    private final String format;

    public Differ(String pathToFile1, String pathToFile2, String format) {
        this.pathToFile1 = Paths.get(pathToFile1).toAbsolutePath().normalize();
        this.pathToFile2 = Paths.get(pathToFile2).toAbsolutePath().normalize();
        this.format = format;
    }


    public List<LinkedHashMap<String, Object>> generateDiff() throws Exception {
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

    private LinkedHashMap<String, Object> getMapDifference(String key,
                                                           Map<String, Object> map1,
                                                           Map<String, Object> map2) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        if (map1.containsKey(key) && map2.containsKey(key)) {
            if (Objects.equals(map1.get(key), map2.get(key))) {
                result.put(key, map1.get(key));
            } else {
                String key1 = "- " + key;
                String key2 = "+ " + key;
                result.put(key1, map1.get(key));
                result.put(key2, map2.get(key));
            }
        } else if (map1.containsKey(key)) {
            String newKey = "- " + key;
            result.put(newKey, map1.get(key));
        } else {
            String newKey = "+ " + key;
            result.put(newKey, map2.get(key));
        }
        return result;
    }
    public String generateString() throws Exception {
        Formatter formatter = FactoryFormatter.getFormatter(format);
        return formatter.generateString(generateDiff());
    }

}
