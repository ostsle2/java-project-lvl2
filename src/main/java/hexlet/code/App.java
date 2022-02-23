package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

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

    @Override
    public String call() throws Exception {
        return Differ.generate(path1, path2, format);
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new App());
        cmd.execute(args);
        String result = cmd.getExecutionResult();
        System.out.println(result);
    }

}
