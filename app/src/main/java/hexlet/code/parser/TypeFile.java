package hexlet.code.parser;

import java.nio.file.Path;

public class TypeFile {
    public static String get(Path filePath) throws Exception {
        String fileName = filePath.getFileName().toString();
        if (!fileName.contains(".")) {
            throw new Exception("wrong file name: " + fileName);
        }
        return fileName.split("\\.")[1];
    }
}
