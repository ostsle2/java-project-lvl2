package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> map1 = Parser.parseToMap(getContentFile(filePath1), filePath1);
        Map<String, Object> map2 = Parser.parseToMap(getContentFile(filePath2), filePath2);

        Set<String> mergedKeys = new TreeSet<>(map1.keySet());
        mergedKeys.addAll(map2.keySet());

        Map<String, Status> diff = buildDiff(mergedKeys, map1, map2);
        return Formatter.getFormatter(formatName).format(diff, map1, map2);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static Map<String, Status> buildDiff(Set<String> mergedKeys,
                                                Map<String, Object> map1,
                                                Map<String, Object> map2) {
        Map<String, Status> resultedMapDiff = new LinkedHashMap<>();

        for (String key : mergedKeys) {
            if ((map1.containsKey(key)) && (map2.containsKey(key))) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    resultedMapDiff.put(key, Status.UNCHANGED);
                } else {
                    resultedMapDiff.put(key, Status.CHANGED);
                }
            } else if ((map1.containsKey(key)) && (!map2.containsKey(key))) {
                resultedMapDiff.put(key, Status.REMOVED);
            } else {
                resultedMapDiff.put(key, Status.ADDED);
            }
        }
        return resultedMapDiff;
    }

    public static Path getNormalizedPath(String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize();
    }

    public static String getContentFile(String fileName) throws IOException {
        return Files.readString(getNormalizedPath(fileName));
    }
}
