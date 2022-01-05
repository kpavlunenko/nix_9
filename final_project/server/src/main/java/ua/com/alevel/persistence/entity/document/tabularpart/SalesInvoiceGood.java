package ua.com.alevel.persistence.entity.document.tabularpart;

import ua.com.alevel.api.dto.request.entity.SalesInvoiceGoodRequestDto;
import ua.com.alevel.api.dto.response.entity.SalesInvoiceGoodResponseDto;
import ua.com.alevel.persistence.entity.BaseTable;
import ua.com.alevel.persistence.entity.directory.Nomenclature;
import ua.com.alevel.persistence.entity.document.SalesInvoice;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales_invoices_goods")
public class SalesInvoiceGood extends BaseTable {

    @ManyToOne
    @JoinColumn (name="sales_invoices_goods_id")
    private SalesInvoice salesInvoice;

    @OneToOne
    private Nomenclature nomenclature;

    @Column(precision = 12, scale = 4)
    private BigDecimal price;

    @Column(precision = 12, scale = 2)
    private BigDecimal quantity;

    @Column(precision = 12, scale = 2)
    private BigDecimal sum;

    public SalesInvoiceGood() {
        super();
    }

    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
