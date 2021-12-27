package ua.com.alevel.api.dto.response.entity;

import ua.com.alevel.api.dto.response.ResponseDto;
import ua.com.alevel.persistence.entity.Nomenclature;

public class NomenclatureResponseDto extends ResponseDto {

    private String name;
    private Boolean product;
    private Boolean service;
    private BusinessDirectionShortResponseDto businessDirection;

    public NomenclatureResponseDto() {

    }

    public NomenclatureResponseDto(Nomenclature nomenclature) {
        setId(nomenclature.getId());
        setCreated(nomenclature.getCreated());
        setUpdated(nomenclature.getUpdated());
        setDeletionMark(nomenclature.isDeletionMark());
        this.name = nomenclature.getName();
        this.product = nomenclature.getProduct();
        this.service = nomenclature.getService();
        this.businessDirection = new BusinessDirectionShortResponseDto(nomenclature.getBusinessDirection());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getProduct() {
        return product;
    }

    public void setProduct(Boolean product) {
        this.product = product;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public BusinessDirectionShortResponseDto getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(BusinessDirectionShortResponseDto businessDirection) {
        this.businessDirection = businessDirection;
    }
}
