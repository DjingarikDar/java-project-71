package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;
    @Parameters(index = "0", description = "path to first file")
    private String strFilePath1;
    @Parameters(index = "1", description = "path to second file")
    private String strFilePath2;

    @Override
    public Integer call() throws Exception {

        var resultDiff = Differ.generate(strFilePath1, strFilePath2, format);
        System.out.println(resultDiff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
