package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.CounterpartyType;

import javax.persistence.*;

@Entity
@Table(name = "counterparties")
public class Counterparty extends BaseEntity {

    private String name;
    private String inn;

    @Enumerated(EnumType.STRING)
    @Column(name = "counterparty_type")
    private CounterpartyType counterpartyType;

    public Counterparty() {
        super();
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
