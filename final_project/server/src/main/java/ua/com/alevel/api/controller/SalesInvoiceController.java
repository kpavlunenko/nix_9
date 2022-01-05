package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.api.dto.request.entity.SalesInvoiceRequestDto;
import ua.com.alevel.api.dto.response.entity.SalesInvoiceResponseDto;
import ua.com.alevel.facade.SalesInvoiceFacade;

import java.util.List;

@RestController
@RequestMapping("/api/sales_invoices")
public class SalesInvoiceController {

    private final SalesInvoiceFacade salesInvoiceFacade;

    public SalesInvoiceController(SalesInvoiceFacade salesInvoiceFacade) {
        this.salesInvoiceFacade = salesInvoiceFacade;
    }

    @GetMapping
    public ResponseEntity<List<SalesInvoiceResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(salesInvoiceFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(salesInvoiceFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewSalesInvoice(@RequestBody SalesInvoiceRequestDto salesInvoiceRequestDto) {
        salesInvoiceFacade.create(salesInvoiceRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateSalesInvoice(@PathVariable Long id, @RequestBody SalesInvoiceRequestDto salesInvoiceRequestDto) {
        salesInvoiceFacade.update(salesInvoiceRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        salesInvoiceFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesInvoiceResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(salesInvoiceFacade.findById(id));
    }

}
