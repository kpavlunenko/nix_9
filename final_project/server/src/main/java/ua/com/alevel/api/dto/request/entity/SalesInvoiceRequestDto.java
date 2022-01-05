package ua.com.alevel.api.dto.request.entity;

import ua.com.alevel.api.dto.request.RequestDto;
import java.util.Date;

public class SalesInvoiceRequestDto extends RequestDto {

    private Date date;
    private long companyId;
    private long counterpartyId;
    private long agreementId;
    private long currencyId;
    private long priceTypeId;
    private SalesInvoiceGoodRequestDto[] salesInvoiceGoods;

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

    public long getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(long priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public SalesInvoiceGoodRequestDto[] getSalesInvoiceGoods() {
        return salesInvoiceGoods;
    }

    public void setSalesInvoiceGoods(SalesInvoiceGoodRequestDto[] salesInvoiceGoods) {
        this.salesInvoiceGoods = salesInvoiceGoods;
    }
}
