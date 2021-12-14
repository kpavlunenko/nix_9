package ua.com.alevel.persistence.type;

public enum OperationType {

    INCOME("income"),
    EXPENSE("expense");

    private final String type;

    OperationType(String type) {
        this.type = type;
    }

}
