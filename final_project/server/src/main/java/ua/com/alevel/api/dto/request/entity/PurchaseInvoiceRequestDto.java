package ua.com.alevel.api.dto.request.entity;

import ua.com.alevel.api.dto.request.RequestDto;

import java.util.Date;

public class PurchaseInvoiceRequestDto extends RequestDto {

    private Date date;
    private long companyId;
    private long counterpartyId;
    private long agreementId;
    private long currencyId;
    private PurchaseInvoiceGoodRequestDto[] purchaseInvoiceGoods;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public long getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(long agreementId) {
        this.agreementId = agreementId;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public PurchaseInvoiceGoodRequestDto[] getPurchaseInvoiceGoods() {
        return purchaseInvoiceGoods;
    }

    public void setPurchaseInvoiceGoods(PurchaseInvoiceGoodRequestDto[] purchaseInvoiceGoods) {
        this.purchaseInvoiceGoods = purchaseInvoiceGoods;
    }
}
