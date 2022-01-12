package ua.com.alevel.api.dto.response.entity;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.api.dto.response.ResponseDto;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

public class PurchaseInvoiceResponseDto extends ResponseDto {

    private Date date;
    private CompanyShortResponseDto company;
    private CounterpartyResponseDto counterparty;
    private AgreementResponseDto agreement;
    private CurrencyResponseDto currency;
    private BigDecimal sum;
    private PurchaseInvoiceGoodResponseDto[] purchaseInvoiceGoods;

    public PurchaseInvoiceResponseDto() {

    }

    public PurchaseInvoiceResponseDto(PurchaseInvoice purchaseInvoice) {
        setId(purchaseInvoice.getId());
        setCreated(purchaseInvoice.getCreated());
        setUpdated(purchaseInvoice.getUpdated());
        setDeletionMark(purchaseInvoice.isDeletionMark());
        this.date = purchaseInvoice.getDate();
        this.company = new CompanyShortResponseDto(purchaseInvoice.getCompany());
        this.counterparty = new CounterpartyResponseDto(purchaseInvoice.getCounterparty());
        this.agreement = new AgreementResponseDto(purchaseInvoice.getAgreement());
        this.currency = new CurrencyResponseDto(purchaseInvoice.getCurrency());
        this.sum = purchaseInvoice.getPurchaseInvoiceGoods().stream()
                .map(good -> good.getSum())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (CollectionUtils.isNotEmpty(purchaseInvoice.getPurchaseInvoiceGoods())){
            this.purchaseInvoiceGoods = purchaseInvoice.getPurchaseInvoiceGoods()
                    .stream().map(PurchaseInvoiceGoodResponseDto::new).collect(Collectors.toList()).toArray(new PurchaseInvoiceGoodResponseDto[0]);
        } else {
            this.purchaseInvoiceGoods = new PurchaseInvoiceGoodResponseDto[0];
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public PurchaseInvoiceGoodResponseDto[] getPurchaseInvoiceGoods() {
        return purchaseInvoiceGoods;
    }

    public void setPurchaseInvoiceGoods(PurchaseInvoiceGoodResponseDto[] purchaseInvoiceGoods) {
        this.purchaseInvoiceGoods = purchaseInvoiceGoods;
    }
}
