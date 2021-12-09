package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.AgreementRequestDto;
import ua.com.alevel.api.dto.response.AgreementResponseDto;
import ua.com.alevel.facade.AgreementFacade;

import java.util.List;

@RestController
@RequestMapping("/api/agreements")
public class AgreementController extends BaseController {

    private final AgreementFacade agreementFacade;

    public AgreementController(AgreementFacade agreementFacade) {
        this.agreementFacade = agreementFacade;
    }

    @GetMapping
    public ResponseEntity<List<AgreementResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(agreementFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(agreementFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewAgreement(@RequestBody AgreementRequestDto agreementRequestDto) {
        agreementFacade.create(agreementRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateAgreement(@PathVariable Long id, @RequestBody AgreementRequestDto agreementRequestDto) {
        agreementFacade.update(agreementRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAgreement(@PathVariable Long id) {
        agreementFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(agreementFacade.findById(id));
    }
}
