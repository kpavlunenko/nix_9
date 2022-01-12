package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.PurchaseInvoiceGoodRequestDto;
import ua.com.alevel.api.dto.request.entity.PurchaseInvoiceRequestDto;
import ua.com.alevel.api.dto.response.entity.PurchaseInvoiceResponseDto;
import ua.com.alevel.facade.PurchaseInvoiceFacade;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;
import ua.com.alevel.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PurchaseInvoiceFacadeImpl implements PurchaseInvoiceFacade {

    private final PurchaseInvoiceService purchaseInvoiceService;
    private final CompanyService companyService;
    private final CounterpartyService counterpartyService;
    private final AgreementService agreementService;
    private final CurrencyService currencyService;
    private final NomenclatureService nomenclatureService;

    public PurchaseInvoiceFacadeImpl(PurchaseInvoiceService purchaseInvoiceService, CompanyService companyService, CounterpartyService counterpartyService,
                                  AgreementService agreementService, CurrencyService currencyService,
                                  NomenclatureService nomenclatureService) {
        this.purchaseInvoiceService = purchaseInvoiceService;
        this.companyService = companyService;
        this.counterpartyService = counterpartyService;
        this.agreementService = agreementService;
        this.currencyService = currencyService;
        this.nomenclatureService = nomenclatureService;
    }

    @Override
    public void create(PurchaseInvoiceRequestDto purchaseInvoiceRequestDto) {
        PurchaseInvoice purchaseInvoice = initializePurchaseInvoice(new PurchaseInvoice(), purchaseInvoiceRequestDto);
        purchaseInvoiceService.create(purchaseInvoice);
    }

    @Override
    public void update(PurchaseInvoiceRequestDto purchaseInvoiceRequestDto, Long id) {
        PurchaseInvoice purchaseInvoice = initializePurchaseInvoice(purchaseInvoiceService.findById(id).get(), purchaseInvoiceRequestDto);
        purchaseInvoice.setUpdated(new Date());
        purchaseInvoiceService.update(purchaseInvoice);
    }

    @Override
    public void delete(Long id) {
        purchaseInvoiceService.delete(id);
    }

    @Override
    public PurchaseInvoiceResponseDto findById(Long id) {
        return new PurchaseInvoiceResponseDto(purchaseInvoiceService.findById(id).get());
    }

    @Override
    public List<PurchaseInvoiceResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<PurchaseInvoice> all = purchaseInvoiceService.findAll(parameterMap);
        List<PurchaseInvoiceResponseDto> items = all.stream().map(PurchaseInvoiceResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return purchaseInvoiceService.count(parameterMap);
    }

    private PurchaseInvoice initializePurchaseInvoice(PurchaseInvoice purchaseInvoice, PurchaseInvoiceRequestDto purchaseInvoiceRequestDto) {
        purchaseInvoice.setDate(purchaseInvoiceRequestDto.getDate());
        purchaseInvoice.setCompany(companyService.findById(purchaseInvoiceRequestDto.getCompanyId()).get());
        purchaseInvoice.setCounterparty(counterpartyService.findById(purchaseInvoiceRequestDto.getCounterpartyId()).get());
        purchaseInvoice.setAgreement(agreementService.findById(purchaseInvoiceRequestDto.getAgreementId()).get());
        purchaseInvoice.setCurrency(currencyService.findById(purchaseInvoiceRequestDto.getCurrencyId()).get());

        List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
        for (PurchaseInvoiceGoodRequestDto purchaseInvoiceGoodRequestDto : purchaseInvoiceRequestDto.getPurchaseInvoiceGoods()) {
            PurchaseInvoiceGood purchaseInvoiceGood = new PurchaseInvoiceGood();
            purchaseInvoiceGood.setNomenclature(nomenclatureService.findById(purchaseInvoiceGoodRequestDto.getNomenclatureId()).get());
            purchaseInvoiceGood.setPrice(purchaseInvoiceGoodRequestDto.getPrice());
            purchaseInvoiceGood.setQuantity(purchaseInvoiceGoodRequestDto.getQuantity());
            purchaseInvoiceGood.setSum(purchaseInvoiceGoodRequestDto.getSum());
            purchaseInvoiceGood.setPurchaseInvoice(purchaseInvoice);
            purchaseInvoiceGoods.add(purchaseInvoiceGood);
        }
        purchaseInvoice.setPurchaseInvoiceGoods(purchaseInvoiceGoods);

        return purchaseInvoice;
    }
}
