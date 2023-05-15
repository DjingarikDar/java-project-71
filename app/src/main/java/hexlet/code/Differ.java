package hexlet.code;

import hexlet.code.calculations.CalculateDifference;
import hexlet.code.formatter.FactoryFormatter;
import hexlet.code.formatter.Formatter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {

    public static String generate(String strFilePath1, String strFilePath2, String format) throws Exception {
        Path pathToFile1 = Paths.get(strFilePath1).toAbsolutePath().normalize();
        Path pathToFile2 = Paths.get(strFilePath2).toAbsolutePath().normalize();
        Formatter formatter = FactoryFormatter.getFormatter(format);
        return formatter.generateString(CalculateDifference.generate(pathToFile1, pathToFile2));
    }
    public static String generate(String strFilePath1, String strFilePath2) throws Exception {
       return generate(strFilePath1, strFilePath2, "stylish");
    }

}
