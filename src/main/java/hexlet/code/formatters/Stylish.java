package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.Map;

public final class Stylish implements Format {

    public String format(Map<String, Status> diffMap,
                         Map<String, Object> firstMap,
                         Map<String, Object> secondMap) {

        StringBuilder builder = new StringBuilder("{" + System.lineSeparator());
        for (Map.Entry<String, Status> entry : diffMap.entrySet()) {
            String key = entry.getKey();
            Status difference = entry.getValue();
            switch (difference) {
                case UNCHANGED -> builder.append(doNotChange(key, firstMap.get(key)));
                case CHANGED -> builder.append(change(key, firstMap.get(key), secondMap.get(key)));
                case REMOVED -> builder.append(remove(key, firstMap.get(key)));
                default -> builder.append(add(key, secondMap.get(key)));
            }
        }
        return builder.append("}").toString();
    }

    private static StringBuilder doNotChange(String key, Object obj) {
        return new StringBuilder("    ")
                .append(key)
                .append(": ")
                .append(obj)
                .append(System.lineSeparator());
    }

    private static StringBuilder change(String key, Object objFirstFile, Object objSecondFile) {
        return new StringBuilder().append(remove(key, objFirstFile)).append(add(key, objSecondFile));
    }

    private static StringBuilder remove(String key, Object obj) {
        return new StringBuilder("  - ")
                .append(key)
                .append(": ")
                .append(obj)
                .append(System.lineSeparator());
    }

    private static StringBuilder add(String key, Object obj) {
        return new StringBuilder("  + ")
                .append(key)
                .append(": ")
                .append(obj)
                .append(System.lineSeparator());
    }
}
