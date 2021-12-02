package ua.com.alevel.view.dto.request;

import ua.com.alevel.type.CounterpartyType;

public class CounterpartyRequestDto extends RequestDto {

    private String name;
    private String inn;
    private CounterpartyType counterpartyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public CounterpartyType getCounterpartyType() {
        return counterpartyType;
    }

    public void setCounterpartyType(CounterpartyType counterpartyType) {
        this.counterpartyType = counterpartyType;
    }
}
