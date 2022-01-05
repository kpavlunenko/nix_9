package ua.com.alevel.api.dto.response.entity;

import ua.com.alevel.api.dto.response.ResponseDto;
import ua.com.alevel.persistence.entity.directory.Counterparty;
import ua.com.alevel.persistence.type.CounterpartyType;

public class CounterpartyResponseDto extends ResponseDto {

    private String name;
    private String inn;
    private CounterpartyType counterpartyType;
    private long countOfAgreements;

    public CounterpartyResponseDto() {
    }

    public CounterpartyResponseDto(Counterparty counterparty) {
        setId(counterparty.getId());
        setCreated(counterparty.getCreated());
        setUpdated(counterparty.getUpdated());
        setDeletionMark(counterparty.isDeletionMark());
        this.name = counterparty.getName();
        this.inn = counterparty.getInn();
        this.counterpartyType = counterparty.getCounterpartyType();
    }

    public long getCountOfAgreements() {
        return countOfAgreements;
    }

    public void setCountOfAgreements(long countOfAgreements) {
        this.countOfAgreements = countOfAgreements;
    }

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
