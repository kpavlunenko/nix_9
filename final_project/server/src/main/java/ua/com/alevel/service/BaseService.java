package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    Optional<ENTITY> findById(Long id);
    List<ENTITY> findAll(Map<String, String[]> parameterMap);
    long count(Map<String, String[]> parameterMap);
}
