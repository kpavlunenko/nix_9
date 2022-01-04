package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.PriceTypeRequestDto;
import ua.com.alevel.api.dto.response.entity.PriceTypeResponseDto;
import ua.com.alevel.facade.PriceTypeFacade;

import java.util.List;

@RestController
@RequestMapping("/api/price_types")
public class PriceTypeController {

    private final PriceTypeFacade priceTypeFacade;

    public PriceTypeController(PriceTypeFacade priceTypeFacade) {
        this.priceTypeFacade = priceTypeFacade;
    }

    @GetMapping
    public ResponseEntity<List<PriceTypeResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(priceTypeFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(priceTypeFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewNomenclature(@RequestBody PriceTypeRequestDto priceTypeRequestDto) {
        priceTypeFacade.create(priceTypeRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateNomenclature(@PathVariable Long id, @RequestBody PriceTypeRequestDto priceTypeRequestDto) {
        priceTypeFacade.update(priceTypeRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        priceTypeFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceTypeResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(priceTypeFacade.findById(id));
    }

}
