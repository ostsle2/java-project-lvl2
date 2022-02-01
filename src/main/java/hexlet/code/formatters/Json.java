package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.Map;

import static hexlet.code.formatters.Plain.isString;

public final class Json implements Format {

    public String format(Map<String, Status> diffMap,
                         Map<String, Object> firstMap,
                         Map<String, Object> secondMap) {

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String key : diffMap.keySet()) {
            builder.append(getStringBlock(key, firstMap, secondMap));
        }
        builder.deleteCharAt(builder.lastIndexOf(",")).deleteCharAt(builder.lastIndexOf("\n"));
        builder.append("]");
        return builder.toString().replaceAll("\r", "");
    }

    private static StringBuilder getStringBlock(String key,
                                                Map<String, Object> map1,
                                                Map<String, Object> map2) {
        return new StringBuilder("{").append(System.lineSeparator())
                .append("  \"key\": ").append("\"").append(key).append("\",").append(System.lineSeparator())
                .append("  \"oldValue\": ")
                .append(isString(map1.get(key)) ? "\"" + map1.get(key) + "\"" : map1.get(key))
                .append(",").append(System.lineSeparator())
                .append("  \"newValue\": ")
                .append(isString(map2.get(key)) ? "\"" + map2.get(key) + "\"" : map2.get(key))
                .append(System.lineSeparator()).append("}").append(",").append(System.lineSeparator());
    }
}
