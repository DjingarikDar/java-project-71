package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface Parser {

    Map<String, Object> toMap(Path filepath) throws IOException;
}
