package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.NomenclatureRequestDto;
import ua.com.alevel.api.dto.response.NomenclatureResponseDto;
import ua.com.alevel.facade.NomenclatureFacade;

import java.util.List;

@RestController
@RequestMapping("/api/nomenclatures")
public class NomenclatureController {

    private final NomenclatureFacade nomenclatureFacade;

    public NomenclatureController(NomenclatureFacade nomenclatureFacade) {
        this.nomenclatureFacade = nomenclatureFacade;
    }

    @GetMapping
    public ResponseEntity<List<NomenclatureResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(nomenclatureFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(nomenclatureFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewNomenclature(@RequestBody NomenclatureRequestDto nomenclatureRequestDto) {
        nomenclatureFacade.create(nomenclatureRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateNomenclature(@PathVariable Long id, @RequestBody NomenclatureRequestDto nomenclatureRequestDto) {
        nomenclatureFacade.update(nomenclatureRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        nomenclatureFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NomenclatureResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(nomenclatureFacade.findById(id));
    }
}
