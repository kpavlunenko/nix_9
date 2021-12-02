package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Agreement;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.type.AgreementType;

public class AgreementResponseDto extends ResponseDto {

    private String name;
    private Company company;
    private Counterparty counterparty;
    private AgreementType agreementType;
    private Long companyId;
    private Long counterpartyId;

    public AgreementResponseDto() {

    }

    public AgreementResponseDto(Agreement agreement) {
        setId(agreement.getId());
        setCreated(agreement.getCreated());
        setUpdated(agreement.getUpdated());
        setDeletionMark(agreement.isDeletionMark());
        this.name = agreement.getName();
        this.agreementType = agreement.getAgreementType();
        this.company = agreement.getCompany();
        this.counterparty = agreement.getCounterparty();
        this.companyId = agreement.getCompany().getId();
        this.counterpartyId = agreement.getCounterparty().getId();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
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
