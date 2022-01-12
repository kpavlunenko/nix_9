package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.PurchaseInvoiceRequestDto;
import ua.com.alevel.api.dto.response.entity.PurchaseInvoiceResponseDto;
import ua.com.alevel.facade.PurchaseInvoiceFacade;

import java.util.List;

@RestController
@RequestMapping("/api/purchase_invoices")
public class PurchaseInvoiceController {

    private final PurchaseInvoiceFacade purchaseInvoiceFacade;

    public PurchaseInvoiceController(PurchaseInvoiceFacade purchaseInvoiceFacade) {
        this.purchaseInvoiceFacade = purchaseInvoiceFacade;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseInvoiceResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(purchaseInvoiceFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(purchaseInvoiceFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewPurchaseInvoice(@RequestBody PurchaseInvoiceRequestDto purchaseInvoiceRequestDto) {
        purchaseInvoiceFacade.create(purchaseInvoiceRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updatePurchaseInvoice(@PathVariable Long id, @RequestBody PurchaseInvoiceRequestDto purchaseInvoiceRequestDto) {
        purchaseInvoiceFacade.update(purchaseInvoiceRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        purchaseInvoiceFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseInvoiceResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseInvoiceFacade.findById(id));
    }
}
