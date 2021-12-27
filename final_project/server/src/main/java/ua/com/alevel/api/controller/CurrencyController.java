package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.CurrencyRequestDto;
import ua.com.alevel.api.dto.response.entity.CurrencyResponseDto;
import ua.com.alevel.facade.CurrencyFacade;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final CurrencyFacade currencyFacade;

    public CurrencyController(CurrencyFacade currencyFacade) {
        this.currencyFacade = currencyFacade;
    }

    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(currencyFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(currencyFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewCurrency(@RequestBody CurrencyRequestDto currencyRequestDto) {
        currencyFacade.create(currencyRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCurrency(@PathVariable Long id, @RequestBody CurrencyRequestDto currencyRequestDto) {
        currencyFacade.update(currencyRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        currencyFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(currencyFacade.findById(id));
    }
}
