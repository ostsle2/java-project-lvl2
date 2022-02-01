package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Paths;
import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<String> {

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String path1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String path2;

    public App() {
    }
    private static final String PATH = "src/test/java/resources/fixtures/";
    @Override
    public String call() throws Exception {
        String s1 = Paths.get(path1).isAbsolute() ? path1 : PATH + path1;
        String s2 = Paths.get(path2).isAbsolute() ? path2 : PATH + path2;
        return Differ.generate(s1, s2, format);
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new App());
        cmd.execute(args);
        String result = cmd.getExecutionResult();
        System.out.println(result);
    }

}
