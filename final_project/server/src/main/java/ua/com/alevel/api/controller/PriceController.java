package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.PriceRequestDto;
import ua.com.alevel.api.dto.response.table.PriceResponseDto;
import ua.com.alevel.facade.PriceFacade;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceFacade priceFacade;

    public PriceController(PriceFacade priceFacade) {
        this.priceFacade = priceFacade;
    }

    @GetMapping
    public ResponseEntity<List<PriceResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(priceFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(priceFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewPrice(@RequestBody PriceRequestDto priceRequestDto) {
        priceFacade.create(priceRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updatePrice(@PathVariable Long id, @RequestBody PriceRequestDto priceRequestDto) {
        priceFacade.update(priceRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        priceFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(priceFacade.findById(id));
    }
}
