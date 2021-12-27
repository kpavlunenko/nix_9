package ua.com.alevel.api.dto.request.entity;

import ua.com.alevel.api.dto.request.RequestDto;

public class NomenclatureRequestDto extends RequestDto {

    private String name;
    private Boolean product;
    private Boolean service;
    private long businessDirectionId;

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

    public long getBusinessDirectionId() {
        return businessDirectionId;
    }

    public void setBusinessDirectionId(long businessDirectionId) {
        this.businessDirectionId = businessDirectionId;
    }
}
