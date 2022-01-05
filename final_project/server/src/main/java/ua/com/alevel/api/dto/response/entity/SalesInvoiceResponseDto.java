package ua.com.alevel.api.dto.response.entity;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.api.dto.response.ResponseDto;
import ua.com.alevel.persistence.entity.document.SalesInvoice;

import java.util.Date;
import java.util.stream.Collectors;

public class SalesInvoiceResponseDto extends ResponseDto {

    private Date date;
    private CompanyShortResponseDto company;
    private CounterpartyResponseDto counterparty;
    private AgreementResponseDto agreement;
    private CurrencyResponseDto currency;
    private PriceTypeResponseDto priceType;
    private SalesInvoiceGoodResponseDto[] salesInvoiceGoods;

    public SalesInvoiceResponseDto() {

    }

    public SalesInvoiceResponseDto(SalesInvoice salesInvoice) {
        setId(salesInvoice.getId());
        setCreated(salesInvoice.getCreated());
        setUpdated(salesInvoice.getUpdated());
        setDeletionMark(salesInvoice.isDeletionMark());
        this.date = salesInvoice.getDate();
        this.company = new CompanyShortResponseDto(salesInvoice.getCompany());
        this.counterparty = new CounterpartyResponseDto(salesInvoice.getCounterparty());
        this.agreement = new AgreementResponseDto(salesInvoice.getAgreement());
        this.currency = new CurrencyResponseDto(salesInvoice.getCurrency());
        this.priceType = new PriceTypeResponseDto(salesInvoice.getPriceType());
        if (CollectionUtils.isNotEmpty(salesInvoice.getSalesInvoiceGoods())){
            this.salesInvoiceGoods = salesInvoice.getSalesInvoiceGoods()
                    .stream().map(SalesInvoiceGoodResponseDto::new).collect(Collectors.toList()).toArray(new SalesInvoiceGoodResponseDto[0]);
        } else {
            this.salesInvoiceGoods = new SalesInvoiceGoodResponseDto[0];
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CompanyShortResponseDto getCompany() {
        return company;
    }

    public void setCompany(CompanyShortResponseDto company) {
        this.company = company;
    }

    public CounterpartyResponseDto getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(CounterpartyResponseDto counterparty) {
        this.counterparty = counterparty;
    }

    public AgreementResponseDto getAgreement() {
        return agreement;
    }

    public void setAgreement(AgreementResponseDto agreement) {
        this.agreement = agreement;
    }

    public CurrencyResponseDto getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyResponseDto currency) {
        this.currency = currency;
    }

    public PriceTypeResponseDto getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceTypeResponseDto priceType) {
        this.priceType = priceType;
    }

    public SalesInvoiceGoodResponseDto[] getSalesInvoiceGoods() {
        return salesInvoiceGoods;
    }

    public void setSalesInvoiceGoods(SalesInvoiceGoodResponseDto[] salesInvoiceGoods) {
        this.salesInvoiceGoods = salesInvoiceGoods;
    }
}
