package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.CategoryRequestDto;
import ua.com.alevel.api.dto.response.CategoryResponseDto;
import ua.com.alevel.facade.CategoryFacade;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.service.CategoryService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryService categoryService;

    public CategoryFacadeImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void create(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setBankOperationType(categoryRequestDto.getBankOperationType());
        categoryService.create(category);
    }

    @Override
    public void update(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = categoryService.findById(id).get();
        category.setUpdated(new Date());
        category.setName(categoryRequestDto.getName());
        category.setBankOperationType(categoryRequestDto.getBankOperationType());
        categoryService.create(category);
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return new CategoryResponseDto(categoryService.findById(id).get());
    }

    @Override
    public List<CategoryResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Category> all = categoryService.findAll(parameterMap);
        List<CategoryResponseDto> items = all.stream().map(CategoryResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return categoryService.count(parameterMap);
    }
}
