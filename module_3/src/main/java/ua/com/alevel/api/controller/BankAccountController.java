package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.BankAccountRequestDto;
import ua.com.alevel.api.dto.response.BankAccountResponseDto;
import ua.com.alevel.facade.BankAccountFacade;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bankAccounts")
public class BankAccountController extends BaseController {

    private final BankAccountFacade bankAccountFacade;

    public BankAccountController(BankAccountFacade bankAccountFacade) {
        this.bankAccountFacade = bankAccountFacade;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(bankAccountFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(bankAccountFacade.count(request));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> findBalanceByBankAccount(@PathVariable Long id) {
        return ResponseEntity.ok(bankAccountFacade.findBalanceByBankAccount(id));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewUser(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        bankAccountFacade.create(bankAccountRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateBankAccount(@PathVariable Long id, @RequestBody BankAccountRequestDto bankAccountRequestDto) {
        bankAccountFacade.update(bankAccountRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        bankAccountFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(bankAccountFacade.findById(id));
    }
}
