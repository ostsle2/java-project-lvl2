package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

    public static Format getFormatter(String formatterName) {
        return switch (formatterName) {
            case "json" -> new Json();
            case "plain" -> new Plain();
            default -> new Stylish();
        };
    }
}
