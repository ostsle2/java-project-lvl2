package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;
import java.util.Map;

public final class Stylish implements Format {

    public String format(List<Map<String, Object>> diffs) {

        StringBuilder sb = new StringBuilder("{");
        for (Map<String, Object> diff : diffs) {
            String fieldName = (String) diff.get("fieldName");
            Status s = (Status) diff.get("status");
            switch (s) {
                case REMOVED -> sb.append("\n  - ").append(fieldName).append(": ").append(diff.get("oldValue"));
                case ADDED -> sb.append("\n  + ").append(fieldName).append(": ").append(diff.get("newValue"));
                case UNCHANGED -> sb.append("\n    ").append(fieldName).append(": ").append(diff.get("oldValue"));
                default -> {
                    sb.append("\n  - ").append(fieldName).append(": ").append(diff.get("oldValue"));
                    sb.append("\n  + ").append(fieldName).append(": ").append(diff.get("newValue"));
                }
            }
        }
        sb.append("\n}");
        return sb.toString();
    }
}
