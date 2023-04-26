package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;
    @Parameters(index = "0", description = "path to first file", defaultValue = "file1.json")
    private String strFilePath1;
    @Parameters(index = "1", description = "path to second file", defaultValue = "file2.json")
    private String strFilePath2;

    @Override
    public Integer call() throws Exception {
        Differ differ = new Differ(strFilePath1, strFilePath2, format);
        var resultDiff = differ.generateString();
        //List resultDiff = Differ.generator(strFilePath1, strFilePath2, format);
        System.out.println(resultDiff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
