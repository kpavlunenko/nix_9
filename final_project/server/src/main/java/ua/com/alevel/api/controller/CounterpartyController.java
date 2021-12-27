package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.CounterpartyRequestDto;
import ua.com.alevel.api.dto.response.entity.CounterpartyResponseDto;
import ua.com.alevel.facade.CounterpartyFacade;

import java.util.List;

@RestController
@RequestMapping("/api/counterparties")
public class CounterpartyController extends BaseController {

    private final CounterpartyFacade counterpartyFacade;

    public CounterpartyController(CounterpartyFacade counterpartyFacade) {
        this.counterpartyFacade = counterpartyFacade;
    }

    @GetMapping
    public ResponseEntity<List<CounterpartyResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(counterpartyFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(counterpartyFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewCounterparty(@RequestBody CounterpartyRequestDto counterpartyRequestDto) {
        counterpartyFacade.create(counterpartyRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCounterparty(@PathVariable Long id, @RequestBody  CounterpartyRequestDto counterpartyRequestDto) {
        counterpartyFacade.update(counterpartyRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        counterpartyFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CounterpartyResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(counterpartyFacade.findById(id));
    }
}
