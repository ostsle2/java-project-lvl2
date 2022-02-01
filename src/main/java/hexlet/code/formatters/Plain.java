package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;
import java.util.Map;


public final class Plain implements Format {

    public String format(Map<String, Status> diffMap,
                         Map<String, Object> firstMap,
                         Map<String, Object> secondMap) {

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Status> entry : diffMap.entrySet()) {
            String key = entry.getKey();
            Status difference = entry.getValue();
            switch (difference) {
                case CHANGED -> builder.append(change(key, firstMap.get(key), secondMap.get(key)));
                case REMOVED -> builder.append(remove(key));
                case ADDED -> builder.append(add(key, secondMap.get(key)));
                case UNCHANGED -> {
                }
                default -> throw new IllegalArgumentException();
            }
        }
        builder.deleteCharAt(builder.lastIndexOf("\n"));
        return builder.toString().replaceAll("\r", "");
    }

    private static boolean isComplex(Object obj) {
        return obj instanceof List || obj instanceof Map || obj instanceof Object[];
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    private static StringBuilder change(String key, Object objFirstContent, Object objSecondContent) {
        return new StringBuilder("Property '")
                .append(key).append("' was updated. From ")
                .append(toString(objFirstContent)).append(" to ")
                .append(toString(objSecondContent)).append(System.lineSeparator());
    }

    private static StringBuilder remove(String key) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was removed")
                .append(System.lineSeparator());
    }

    private static StringBuilder add(String key, Object obj) {
        return new StringBuilder("Property '")
                .append(key).append("' was added with value: ")
                .append(toString(obj)).append(System.lineSeparator());
    }

    private static String toString(Object obj) {
        return isComplex(obj) ? "[complex value]" : isString(obj) ? "'" + obj + "'" : String.valueOf(obj);
    }
}
