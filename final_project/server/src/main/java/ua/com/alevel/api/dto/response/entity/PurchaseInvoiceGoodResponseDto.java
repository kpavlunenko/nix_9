package ua.com.alevel.api.dto.response.entity;

import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;

import java.math.BigDecimal;

public class PurchaseInvoiceGoodResponseDto {

    private Long id;
    private NomenclatureResponseDto nomenclature;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal sum;

    public PurchaseInvoiceGoodResponseDto() {

    }

    public PurchaseInvoiceGoodResponseDto(PurchaseInvoiceGood purchaseInvoiceGood) {
        this.id = purchaseInvoiceGood.getId();
        this.nomenclature = new NomenclatureResponseDto(purchaseInvoiceGood.getNomenclature());
        this.price = purchaseInvoiceGood.getPrice();
        this.quantity = purchaseInvoiceGood.getQuantity();
        this.sum = purchaseInvoiceGood.getSum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NomenclatureResponseDto getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(NomenclatureResponseDto nomenclature) {
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
