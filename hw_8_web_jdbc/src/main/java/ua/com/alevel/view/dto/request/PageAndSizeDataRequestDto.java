package ua.com.alevel.view.dto.request;

public class PageAndSizeDataRequestDto {

    int page;
    int size;

    public PageAndSizeDataRequestDto() { }

    public PageAndSizeDataRequestDto(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageAndSizeData{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
