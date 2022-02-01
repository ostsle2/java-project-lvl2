package hexlet.code;

public enum Status {
    UNCHANGED("unchanged"),
    CHANGED("changed"),
    ADDED("added"),
    REMOVED("removed");

    Status(String str) {
        this.value = str;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
