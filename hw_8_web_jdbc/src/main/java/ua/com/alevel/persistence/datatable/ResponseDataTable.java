package ua.com.alevel.persistence.datatable;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ResponseDataTable <ENTITY extends BaseEntity> {

    private List<ENTITY> items;
    private long itemsSize;
    private long entriesFrom;
    private long entriesTo;
    private long totalPageSize;
    private Map<Object, Object> otherParamMap;

    public ResponseDataTable() {
        items = Collections.emptyList();
        otherParamMap = Collections.emptyMap();
        itemsSize = 0;
    }

    public long getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(long totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public long getEntriesFrom() {
        return entriesFrom;
    }

    public void setEntriesFrom(long entriesFrom) {
        this.entriesFrom = entriesFrom;
    }

    public long getEntriesTo() {
        return entriesTo;
    }

    public void setEntriesTo(long entriesTo) {
        this.entriesTo = entriesTo;
    }

    public List<ENTITY> getItems() {
        return items;
    }

    public void setItems(List<ENTITY> items) {
        this.items = items;
    }

    public long getItemsSize() {
        return itemsSize;
    }

    public void setItemsSize(long itemsSize) {
        this.itemsSize = itemsSize;
    }

    public Map<Object, Object> getOtherParamMap() {
        return otherParamMap;
    }

    public void setOtherParamMap(Map<Object, Object> otherParamMap) {
        this.otherParamMap = otherParamMap;
    }
}
