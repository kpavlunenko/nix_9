package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Map;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    List<ENTITY> findAll(Map<String, String[]> parameterMap);
    long count(Map<String, String[]> parameterMap);
}
