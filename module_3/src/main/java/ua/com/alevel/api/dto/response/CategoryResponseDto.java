package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.type.BankOperationType;

public class CategoryResponseDto extends ResponseDto {

    private String name;
    private BankOperationType bankOperationType;

    public CategoryResponseDto() {

    }

    public CategoryResponseDto(Category category) {
        setId(category.getId());
        setCreated(category.getCreated());
        setUpdated(category.getUpdated());
        setDeletionMark(category.isDeletionMark());
        setName(category.getName());
        setBankOperationType(category.getBankOperationType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }

    public void setBankOperationType(BankOperationType bankOperationType) {
        this.bankOperationType = bankOperationType;
    }
}
