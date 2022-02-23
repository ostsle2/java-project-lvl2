package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;
import java.util.Map;


public final class Plain implements Format {

    public String format(List<Map<String, Object>> diffs) {

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> diff : diffs) {
            String fieldName = (String) diff.get("fieldName");
            Status s = (Status) diff.get("status");
            ((Status) diff.get("status")).getValue();
            switch (s) {
                case REMOVED -> sb.append(String.format("Property '%s' was removed%n", fieldName));
                case ADDED -> sb.append(String.format("Property '%s' was added with value: %s%n", fieldName,
                        toString(diff.get("newValue"))));
                case CHANGED -> sb.append(String.format("Property '%s' was updated. From %s to %s%n", fieldName,
                        toString(diff.get("oldValue")),
                        toString(diff.get("newValue"))));
                case UNCHANGED -> {
                }
                default -> throw new IllegalArgumentException();
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private static boolean isComplex(Object obj) {
        return obj instanceof List || obj instanceof Map || obj instanceof Object[];
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    private static String toString(Object obj) {
        return isComplex(obj) ? "[complex value]" : isString(obj) ? "'" + obj + "'" : String.valueOf(obj);
    }
}
