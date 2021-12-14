package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.CategoryRequestDto;
import ua.com.alevel.api.dto.response.CategoryResponseDto;
import ua.com.alevel.facade.CategoryFacade;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {

    private final CategoryFacade categoryFacade;

    public CategoryController(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(categoryFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(categoryFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewUser(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryFacade.create(categoryRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCompany(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryFacade.update(categoryRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        categoryFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(categoryFacade.findById(id));
    }
}
