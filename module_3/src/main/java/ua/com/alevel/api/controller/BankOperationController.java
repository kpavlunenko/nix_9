package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.BankOperationRequestDto;
import ua.com.alevel.api.dto.response.BankOperationResponseDto;
import ua.com.alevel.facade.BankOperationFacade;

import java.util.List;

@RestController
@RequestMapping("/api/bankOperations")
public class BankOperationController extends BaseController {

    private final BankOperationFacade bankOperationFacade;

    public BankOperationController(BankOperationFacade bankOperationFacade) {
        this.bankOperationFacade = bankOperationFacade;
    }

    @GetMapping
    public ResponseEntity<List<BankOperationResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(bankOperationFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(bankOperationFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewUser(@RequestBody BankOperationRequestDto bankOperationRequestDto) {
        bankOperationFacade.create(bankOperationRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCompany(@PathVariable Long id, @RequestBody BankOperationRequestDto bankOperationRequestDto) {
        bankOperationFacade.update(bankOperationRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        bankOperationFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankOperationResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(bankOperationFacade.findById(id));
    }
}
