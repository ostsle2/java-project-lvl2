package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> map1 = Parser.parse(getFileContent(filePath1), getFileExtension(filePath1));
        Map<String, Object> map2 = Parser.parse(getFileContent(filePath2), getFileExtension(filePath2));
        return Formatter.getFormatter(formatName).format(buildDiff(map1, map2));
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static List<Map<String, Object>> buildDiff(
            Map<String, Object> data1, Map<String, Object> data2) {

        List<Map<String, Object>> diffs = new ArrayList<>();
        Set<String> mergedKeys = new TreeSet<>(data1.keySet());
        mergedKeys.addAll(data2.keySet());
        for (String key : mergedKeys) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (!(data2.containsKey(key))) {
                map.put("status", Status.REMOVED);
                map.put("fieldName", key);
                map.put("oldValue", data1.get(key));
            } else if (!(data1.containsKey(key))) {
                map.put("status", Status.ADDED);
                map.put("fieldName", key);
                map.put("newValue", data2.get(key));
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                map.put("status", Status.CHANGED);
                map.put("fieldName", key);
                map.put("oldValue", data1.get(key));
                map.put("newValue", data2.get(key));
            } else {
                map.put("status", Status.UNCHANGED);
                map.put("fieldName", key);
                map.put("oldValue", data1.get(key));
                map.put("newValue", data2.get(key));
            }
            diffs.add(map);
        }
        return diffs;
    }

    public static String getFileContent(String fileName) throws IOException {
        return Files.readString(Paths.get(fileName).toAbsolutePath().normalize());
    }

    private static String getFileExtension(String pathToFile) {
        return pathToFile.substring(pathToFile.indexOf(".") + 1);
    }
}
