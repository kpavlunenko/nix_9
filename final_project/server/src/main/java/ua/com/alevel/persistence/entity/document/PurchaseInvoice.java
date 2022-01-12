package ua.com.alevel.persistence.entity.document;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.directory.Agreement;
import ua.com.alevel.persistence.entity.directory.Company;
import ua.com.alevel.persistence.entity.directory.Counterparty;
import ua.com.alevel.persistence.entity.directory.Currency;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase_invoices")
public class PurchaseInvoice extends BaseEntity {

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

    @OneToMany (mappedBy="purchaseInvoice", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    private List<PurchaseInvoiceGood> purchaseInvoiceGoods;

    public PurchaseInvoice() {
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

    public List<PurchaseInvoiceGood> getPurchaseInvoiceGoods() {
        return purchaseInvoiceGoods;
    }

    public void setPurchaseInvoiceGoods(List<PurchaseInvoiceGood> purchaseInvoiceGoods) {
        this.purchaseInvoiceGoods = purchaseInvoiceGoods;
    }
}
