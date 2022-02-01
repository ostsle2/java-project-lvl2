package hexlet.code.formatters;

import hexlet.code.Status;

import java.io.IOException;
import java.util.Map;

public interface Format {
    String format(Map<String, Status> diffMap,
                  Map<String, Object> firstMap,
                  Map<String, Object> secondMap) throws IOException;
}
