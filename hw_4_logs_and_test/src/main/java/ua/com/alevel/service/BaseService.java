package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findById(String id);
    ENTITY [] findAll();
}
