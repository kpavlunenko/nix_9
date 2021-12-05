package ua.com.alevel.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.type.CompanyType;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypesController extends BaseController {


    @GetMapping("/companyTypes")
    public ResponseEntity<List<CompanyType>> findAll() {
        return ResponseEntity.ok(Arrays.stream(CompanyType.values()).toList());
    }

}
