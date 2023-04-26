package hexlet.code.parser;

import java.nio.file.Path;

public class FactoryParser {
    private static Parser createParser(ParserType type) {
        return switch (type) {
            case JSON -> new JSONParser();
            case YAML -> new YAMLParser();
        };
    }
    public static Parser getParser(Path pathToFile1, Path pathToFile2) throws Exception {
        String fileType1 = TypeFile.get(pathToFile1);
        String fileType2 = TypeFile.get(pathToFile2);
        if (fileType2.equals(fileType1)) {
            return switch (fileType1) {
                case "json" -> FactoryParser.createParser(ParserType.JSON);
                case "yml" -> FactoryParser.createParser(ParserType.YAML);
                default -> throw new Exception("wrong file type : " + fileType1);
            };
        } else {
            throw new Exception("files have different types \n"
                    + pathToFile1
                    + "\n"
                    + pathToFile2);
        }

    }
}
