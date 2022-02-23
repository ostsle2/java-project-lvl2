package hexlet.code;

public enum Status {
    UNCHANGED("unchanged"),
    CHANGED("changed"),
    ADDED("added"),
    REMOVED("removed");

    Status(String str) {
        this.value = str;
    }

    private final String value;

    public String getValue() {
        return value;
    }

}
