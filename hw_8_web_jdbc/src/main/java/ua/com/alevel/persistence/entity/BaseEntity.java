package ua.com.alevel.persistence.entity;

import java.util.Date;

public abstract class BaseEntity {

    private Long id;
    private Date created;
    private Date updated;
    private Boolean deletionMark;

    public BaseEntity() {
        this.created = new Date();
        this.updated = new Date();
        this.deletionMark = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isDeletionMark() {
        return deletionMark;
    }

    public void setDeletionMark(boolean deletionMark) {
        this.deletionMark = deletionMark;
    }
}
