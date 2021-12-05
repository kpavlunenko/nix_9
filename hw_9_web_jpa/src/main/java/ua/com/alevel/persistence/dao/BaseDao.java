package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    boolean existById(Long id);
    ENTITY findById(Long id);
    List<ENTITY> findAll();
    long count();

}
