package ua.com.alevel.persistence.type;

public enum BankOperationType {

    INCOME_SALARY("salary", OperationType.INCOME),
    INCOME_OTHER("other income", OperationType.INCOME),
    INCOME_TRANSFER("transfer to card", OperationType.INCOME),

    OUTCOME_PAYMENT("payment", OperationType.EXPENSE),
    OUTCOME_TRANSFER("transfer from card", OperationType.EXPENSE),
    OUTCOME_CREDIT("loan repayment", OperationType.EXPENSE),
    OUTCOME_OTHER("other expense", OperationType.EXPENSE);

    private final String name;
    private final OperationType type;

    BankOperationType(String name, OperationType type) {
        this.name = name;
        this.type = type;
    }
}
