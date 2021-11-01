package ua.com.alevel.type;

public enum ApplicationType {

    DB_TYPE("db"),
    DB_ARRAY_VALUE("array");

    ApplicationType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}
