package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseService <ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    ResponseDataTable<ENTITY> findAll(RequestDataTable request);
    List<ENTITY> findAll();
}
