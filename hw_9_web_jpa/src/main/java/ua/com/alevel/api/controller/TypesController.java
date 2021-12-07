package ua.com.alevel.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.type.AgreementType;
import ua.com.alevel.type.CompanyType;
import ua.com.alevel.type.CounterpartyType;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypesController extends BaseController {


    @GetMapping("/companyTypes")
    public ResponseEntity<List<CompanyType>> findAllCompanyTypes() {
        return ResponseEntity.ok(Arrays.stream(CompanyType.values()).toList());
    }

    @GetMapping("/counterpartyTypes")
    public ResponseEntity<List<CounterpartyType>> findAllCounterpartyTypes() {
        return ResponseEntity.ok(Arrays.stream(CounterpartyType.values()).toList());
    }

    @GetMapping("/agreementTypes")
    public ResponseEntity<List<AgreementType>> findAllAgreementTypes() {
        return ResponseEntity.ok(Arrays.stream(AgreementType.values()).toList());
    }

}
