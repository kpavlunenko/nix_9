package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.repository.CategoryRepository;
import ua.com.alevel.service.CategoryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CrudRepositoryHelper<Category, CategoryRepository> repositoryHelper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CrudRepositoryHelper<Category, CategoryRepository> repositoryHelper, CategoryRepository categoryRepository) {
        this.repositoryHelper = repositoryHelper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void create(Category entity) {
        checkInputDataOnValid(entity);
        LOGGER_INFO.info("object: Category; stage: start; operation: create");
        repositoryHelper.create(categoryRepository, entity);
        LOGGER_INFO.info("object: Category; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(Category entity) {
        checkInputDataOnValid(entity);
        LOGGER_INFO.info("object: Category; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(categoryRepository, entity);
        LOGGER_INFO.info("object: Category; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOGGER_WARN.warn("object: Category; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(categoryRepository, id);
        LOGGER_WARN.warn("object: Category; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return repositoryHelper.findById(categoryRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(categoryRepository, parameterMap, Category.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(categoryRepository, parameterMap, Category.class);
    }

    private void checkInputDataOnValid(Category entity) {
        if (!nameIsValid(entity.getName())) {
            LOGGER_ERROR.error("object: Category; operation: update/create; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
