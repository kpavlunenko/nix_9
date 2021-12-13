package ua.com.alevel.persistence.crud;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CrudRepositoryHelper<E extends BaseEntity, R extends AbstractRepository<E>> {

    void create(R repository, E entity);
    void update(R repository, E entity);
    void delete(R repository, Long id);
    Optional<E> findById(R repository, Long id);
    List<E> findAll(R repository, Map<String, String[]> parameterMap, Class<E> entityClass);
    long count(R repository, Map<String, String[]> parameterMap, Class<E> entityClass);
}
