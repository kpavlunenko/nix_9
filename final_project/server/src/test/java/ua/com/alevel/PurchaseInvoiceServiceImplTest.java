package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.entity.directory.*;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.persistence.type.CounterpartyType;
import ua.com.alevel.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PurchaseInvoiceServiceImplTest {

    private final CompanyService companyService;
    private final CounterpartyService counterpartyService;
    private final AgreementService agreementService;
    private final NomenclatureService nomenclatureService;
    private final CurrencyService currencyService;
    private final CurrencyRateService currencyRateService;
    private final PurchaseInvoiceService purchaseInvoiceService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    public PurchaseInvoiceServiceImplTest(CompanyService companyService, CounterpartyService counterpartyService, AgreementService agreementService, NomenclatureService nomenclatureService, CurrencyService currencyService, CurrencyRateService currencyRateService, PurchaseInvoiceService purchaseInvoiceService, Map<String, String[]> parameterMap) {
        this.companyService = companyService;
        this.counterpartyService = counterpartyService;
        this.agreementService = agreementService;
        this.nomenclatureService = nomenclatureService;
        this.currencyService = currencyService;
        this.currencyRateService = currencyRateService;
        this.purchaseInvoiceService = purchaseInvoiceService;
        this.parameterMap = parameterMap;
    }

    @BeforeAll
    void init() {
        Company company = GenerationUtil.generateCompany(GenerationUtil.NAME_COMPANY);
        companyService.create(company);
        Counterparty counterparty = GenerationUtil.generateCounterparty(GenerationUtil.NAME_COUNTERPARTY, "111111111111", CounterpartyType.SUPPLIER);
        counterpartyService.create(counterparty);
        Agreement agreement = GenerationUtil.generateAgreement(GenerationUtil.NAME_AGREEMENT, company, counterparty);
        agreementService.create(agreement);
        Nomenclature nomenclature = GenerationUtil.generateNomenclature(GenerationUtil.NAME_NOMENCLATURE);
        nomenclatureService.create(nomenclature);
        Currency currency = GenerationUtil.generateCurrency("UAH", "980");
        currencyService.create(currency);
        CurrencyRate currencyRate = GenerationUtil.generateCurrencyRate(currency, new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 20L), 1);
        currencyRateService.create(currencyRate);
        currency = GenerationUtil.generateCurrency("USD", "840");
        currencyService.create(currency);
        currencyRate = GenerationUtil.generateCurrencyRate(currency, new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 20L), 28);
        currencyRateService.create(currencyRate);

        for (int i = 0; i < ITEMS_SIZE; i++) {
            PurchaseInvoiceGood purchaseInvoiceGood = GenerationUtil.generatePurchaseInvoiceGood(nomenclature, 150, 2);
            List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
            purchaseInvoiceGoods.add(purchaseInvoiceGood);
            PurchaseInvoice purchaseInvoice = GenerationUtil.generatePurchaseInvoice(
                    company, counterparty, agreement, currency,
                    new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * (5L + i)), purchaseInvoiceGoods);
            companyService.create(company);
        }

        Assertions.assertEquals(ITEMS_SIZE, companyService.findAll(parameterMap).size());
    }
}
