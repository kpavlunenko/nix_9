package ua.com.alevel.api.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CompanyFacade;
import ua.com.alevel.api.dto.request.CompanyRequestDto;
import ua.com.alevel.api.dto.response.CompanyResponseDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
public class CompanyController extends BaseController {

    private final CompanyFacade companyFacade;

    public CompanyController(CompanyFacade companyFacade) {
        this.companyFacade = companyFacade;
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(companyFacade.findAll());
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        companyFacade.create(companyRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCompany(@PathVariable Long id, @RequestBody CompanyRequestDto companyRequestDto) {
        companyFacade.update(companyRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        companyFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(companyFacade.findById(id));
    }
}
