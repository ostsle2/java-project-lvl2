package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseToMap(String contents, String type) throws IOException {
        return getObjectMapper(type).readValue(contents, new TypeReference<>() {
        });
    }

    private static ObjectMapper getObjectMapper(String fileExtension) {
        if (fileExtension.endsWith(".json")) {
            return new ObjectMapper();
        } else if (fileExtension.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        } else {
            throw new RuntimeException("Cannot parse file with extension: " + fileExtension);
        }
    }
}
