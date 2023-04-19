package hexlet.code;

public class FactoryParser {
    public static Parser createParser(ParserType type) {
        return switch (type) {
            case JSON -> new JSONParser();
            case YAML -> new YAMLParser();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
