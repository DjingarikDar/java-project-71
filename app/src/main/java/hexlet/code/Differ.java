package hexlet.code;

import hexlet.code.calculations.CalculateDifference;
import hexlet.code.formatter.FactoryFormatter;
import hexlet.code.formatter.Formatter;

import java.nio.file.Path;

public class Differ {

    public static String generate(Path pathToFile1, Path pathToFile2, String format) throws Exception {
        Formatter formatter = FactoryFormatter.getFormatter(format);
        return formatter.generateString(CalculateDifference.generate(pathToFile1, pathToFile2));
    }

}
