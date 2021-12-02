package ua.com.alevel.view.dto.request;

public class SortDataRequestDto {

    private String sort;
    private String order;

    public SortDataRequestDto() { }

    public SortDataRequestDto(String sort, String order) {
        this.sort = sort;
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "SortData{" +
                "sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
