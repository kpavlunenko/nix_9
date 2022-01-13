package ua.com.alevel.persistence.entity.document;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.directory.Agreement;
import ua.com.alevel.persistence.entity.directory.Company;
import ua.com.alevel.persistence.entity.directory.Counterparty;
import ua.com.alevel.persistence.entity.directory.Currency;
import ua.com.alevel.persistence.entity.document.tabularpart.SalesInvoiceGood;
import ua.com.alevel.persistence.entity.directory.PriceType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales_invoices")
public class SalesInvoice extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne
    private Company company;

    @OneToOne
    private Counterparty counterparty;

    @OneToOne
    private Agreement agreement;

    @OneToOne
    private Currency currency;

    @OneToOne
    private PriceType priceType;

    @OneToMany (mappedBy="salesInvoice", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    private List<SalesInvoiceGood> salesInvoiceGoods;

    public SalesInvoice() {
        super();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public List<SalesInvoiceGood> getSalesInvoiceGoods() {
        return salesInvoiceGoods;
    }

    public void setSalesInvoiceGoods(List<SalesInvoiceGood> salesInvoiceGoods) {
        this.salesInvoiceGoods = salesInvoiceGoods;
    }
}
