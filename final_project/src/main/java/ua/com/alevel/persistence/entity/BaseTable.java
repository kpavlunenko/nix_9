package ua.com.alevel.persistence.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BaseTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
