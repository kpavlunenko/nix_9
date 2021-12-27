package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.BusinessDirectionRequestDto;
import ua.com.alevel.api.dto.response.entity.BusinessDirectionResponseDto;
import ua.com.alevel.facade.BusinessDirectionFacade;

import java.util.List;

@RestController
@RequestMapping("/api/business_directions")
public class BusinessDirectionController extends BaseController {

    private final BusinessDirectionFacade businessDirectionFacade;

    public BusinessDirectionController(BusinessDirectionFacade businessDirectionFacade) {
        this.businessDirectionFacade = businessDirectionFacade;
    }

    @GetMapping
    public ResponseEntity<List<BusinessDirectionResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(businessDirectionFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(businessDirectionFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewBusinessDirection(@RequestBody BusinessDirectionRequestDto businessDirectionRequestDto) {
        businessDirectionFacade.create(businessDirectionRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateBusinessDirection(@PathVariable Long id, @RequestBody BusinessDirectionRequestDto businessDirectionRequestDto) {
        businessDirectionFacade.update(businessDirectionRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        businessDirectionFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDirectionResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(businessDirectionFacade.findById(id));
    }
}
