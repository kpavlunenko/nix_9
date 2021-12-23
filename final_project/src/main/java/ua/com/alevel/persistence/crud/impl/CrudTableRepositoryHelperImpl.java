package ua.com.alevel.persistence.crud.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudTableRepositoryHelper;
import ua.com.alevel.persistence.entity.BaseTable;
import ua.com.alevel.persistence.repository.AbstractTableRepository;
import ua.com.alevel.persistence.specification.AbstractTableSpecification;
import ua.com.alevel.persistence.specification.impl.AbstractTableSpecificationImpl;
import ua.com.alevel.util.PageAndSortUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CrudTableRepositoryHelperImpl<
        E extends BaseTable,
        R extends AbstractTableRepository<E>>
        implements CrudTableRepositoryHelper<E, R> {

    @Override
    public void create(R repository, E entity) {
        repository.save(entity);
    }

    @Override
    public void update(R repository, E entity) {
        checkExist(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(R repository, Long id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll(R repository, Map<String, String[]> parameterMap, Class<E> entityClass) {
        Specification<E> specification = null;
        if (MapUtils.isNotEmpty(parameterMap)) {
            AbstractTableSpecification<E> abstractSpecification = new AbstractTableSpecificationImpl<>();
            specification = abstractSpecification.generateCriteriaPredicate(parameterMap, entityClass);
        }

        Sort sort = PageAndSortUtil.generateSort(parameterMap);
        int page = PageAndSortUtil.generatePage(parameterMap);
        int size = PageAndSortUtil.generateSize(repository, parameterMap);
        if (size == 0) {
            size = 10;
        }
        PageRequest request = PageRequest.of(page, size, sort);

        Page<E> pageEntity;
        if (specification == null) {
            pageEntity = repository.findAll(request);
        } else {
            pageEntity = repository.findAll(specification, request);
        }

        return pageEntity.getContent();
    }

    @Override
    public long count(R repository, Map<String, String[]> parameterMap, Class<E> entityClass) {
        Specification<E> specification = null;
        if (MapUtils.isNotEmpty(parameterMap)) {
            AbstractTableSpecification<E> abstractSpecification = new AbstractTableSpecificationImpl<>();
            specification = abstractSpecification.generateCriteriaPredicate(parameterMap, entityClass);
        }
        if (specification == null) {
            return repository.count();
        } else {
            return repository.count(specification);
        }
    }

    private void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("entity not found with id " + id);
        }
    }
}
