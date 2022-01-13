package ua.com.alevel.persistence.entity.register;

import ua.com.alevel.persistence.entity.BaseTable;
import ua.com.alevel.persistence.entity.directory.Company;
import ua.com.alevel.persistence.entity.directory.Nomenclature;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "stock_of_goods")
public class StockOfGood extends BaseTable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private long documentId;

    private String documentName;

    @OneToOne
    private Company company;

    @OneToOne
    private PurchaseInvoice consignment;

    @OneToOne
    private Nomenclature nomenclature;

    @Column(precision = 12, scale = 2)
    private BigDecimal quantity;

    @Column(precision = 12, scale = 2)
    private BigDecimal cost;

    public StockOfGood() {
        super();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PurchaseInvoice getConsignment() {
        return consignment;
    }

    public void setConsignment(PurchaseInvoice consignment) {
        this.consignment = consignment;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
