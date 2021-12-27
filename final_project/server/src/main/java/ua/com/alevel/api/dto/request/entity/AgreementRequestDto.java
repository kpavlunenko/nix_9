package ua.com.alevel.api.dto.request.entity;

import ua.com.alevel.api.dto.request.RequestDto;
import ua.com.alevel.persistence.type.AgreementType;

public class AgreementRequestDto extends RequestDto {

    private String name;
    private long companyId;
    private long counterpartyId;
    private AgreementType agreementType;

    public String getName() {
        return name;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(long counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgreementType getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(AgreementType agreementType) {
        this.agreementType = agreementType;
    }
}
