package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
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
        repositoryHelper.create(categoryRepository, entity);
    }

    @Override
    @Transactional
    public void update(Category entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(categoryRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryHelper.delete(categoryRepository, id);
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
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
