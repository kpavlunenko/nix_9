package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.AgreementType;

import javax.persistence.*;

@Entity
@Table(name = "agreements")
public class Agreement extends BaseEntity {

    private String name;
    @OneToOne
    private Company company;

    @OneToOne
    private Counterparty counterparty;

    @Enumerated(EnumType.STRING)
    @Column(name = "agreement_type")
    private AgreementType agreementType;

    public Agreement() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public AgreementType getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(AgreementType agreementType) {
        this.agreementType = agreementType;
    }
}
