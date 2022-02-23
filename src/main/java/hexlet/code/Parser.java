package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String contents, String type) throws IOException {
        final ObjectMapper objectMapper = switch (type) {
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            case "json" -> new ObjectMapper();
            default -> throw new RuntimeException();
        };
        return objectMapper.readValue(contents, new TypeReference<>() {
        });
    }
}
