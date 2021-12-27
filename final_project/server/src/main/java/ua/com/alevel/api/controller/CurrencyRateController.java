package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.CurrencyRateRequestDto;
import ua.com.alevel.api.dto.response.table.CurrencyRateResponseDto;
import ua.com.alevel.facade.CurrencyRateFacade;

import java.util.List;

@RestController
@RequestMapping("/api/currency_rates")
public class CurrencyRateController {

    private final CurrencyRateFacade currencyRateFacade;

    public CurrencyRateController(CurrencyRateFacade currencyRateFacade) {
        this.currencyRateFacade = currencyRateFacade;
    }

    @GetMapping
    public ResponseEntity<List<CurrencyRateResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(currencyRateFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(currencyRateFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewCurrencyRate(@RequestBody CurrencyRateRequestDto currencyRateRequestDto) {
        currencyRateFacade.create(currencyRateRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCurrencyRate(@PathVariable Long id, @RequestBody CurrencyRateRequestDto currencyRateRequestDto) {
        currencyRateFacade.update(currencyRateRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        currencyRateFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRateResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(currencyRateFacade.findById(id));
    }
}
