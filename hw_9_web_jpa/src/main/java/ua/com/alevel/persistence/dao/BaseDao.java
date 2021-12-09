package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Map;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    boolean existById(Long id);
    ENTITY findById(Long id);
    List<ENTITY> findAll(Map<String, String[]> parameterMap);
    long count(Map<String, String[]> parameterMap);

}
