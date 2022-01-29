package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.*;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.persistence.entity.register.StockOfGood;
import ua.com.alevel.persistence.type.CounterpartyType;
import ua.com.alevel.service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PurchaseInvoiceServiceImplTest {

    @Value("${accountingCurrency.code}")
    private String accountingCurrencyCode;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CounterpartyService counterpartyService;
    @Autowired
    private AgreementService agreementService;
    @Autowired
    private NomenclatureService nomenclatureService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyRateService currencyRateService;
    @Autowired
    private PurchaseInvoiceService purchaseInvoiceService;
    @Autowired
    private StockOfGoodService stockOfGoodService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

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
        Currency currency = GenerationUtil.generateCurrency("UAH", accountingCurrencyCode);
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
            purchaseInvoiceService.create(purchaseInvoice);
        }

        Assertions.assertEquals(ITEMS_SIZE, purchaseInvoiceService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateCompanyWhenNameIsNotEmpty() {
        PurchaseInvoiceGood purchaseInvoiceGood = GenerationUtil.generatePurchaseInvoiceGood(
                nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                150, 2);
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
        purchaseInvoiceGoods.add(purchaseInvoiceGood);
        PurchaseInvoice purchaseInvoice = GenerationUtil.generatePurchaseInvoice(
                companyService.findAll(parameterMap).stream().findAny().get(),
                counterpartyService.findAll(parameterMap).stream().findAny().get(),
                agreementService.findAll(parameterMap).stream().findAny().get(),
                currencyService.findAll(parameterMap).stream().findAny().get(),
                new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 4L),
                purchaseInvoiceGoods);
        purchaseInvoiceService.create(purchaseInvoice);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(1)
    @Test
    void shouldBeReturnExceptionWhenCreateWhenPurchaseInvoiceGoodIsEmpty() {
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
        PurchaseInvoice purchaseInvoice = GenerationUtil.generatePurchaseInvoice(
                companyService.findAll(parameterMap).stream().findAny().get(),
                counterpartyService.findAll(parameterMap).stream().findAny().get(),
                agreementService.findAll(parameterMap).stream().findAny().get(),
                currencyService.findAll(parameterMap).stream().findAny().get(),
                new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 4L),
                purchaseInvoiceGoods);

        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            purchaseInvoiceService.create(purchaseInvoice);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenPriceIsZeroInPurchaseInvoiceGood() {
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
        PurchaseInvoiceGood purchaseInvoiceGood = GenerationUtil.generatePurchaseInvoiceGood(
                nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                0, 2);
        purchaseInvoiceGoods.add(purchaseInvoiceGood);
        PurchaseInvoice purchaseInvoice = GenerationUtil.generatePurchaseInvoice(
                companyService.findAll(parameterMap).stream().findAny().get(),
                counterpartyService.findAll(parameterMap).stream().findAny().get(),
                agreementService.findAll(parameterMap).stream().findAny().get(),
                currencyService.findAll(parameterMap).stream().findAny().get(),
                new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 4L),
                purchaseInvoiceGoods);

        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            purchaseInvoiceService.create(purchaseInvoice);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeReturnExceptionWhenQuantityIsZeroInPurchaseInvoiceGood() {
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
        PurchaseInvoiceGood purchaseInvoiceGood = GenerationUtil.generatePurchaseInvoiceGood(
                nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                150, 0);
        purchaseInvoiceGoods.add(purchaseInvoiceGood);
        PurchaseInvoice purchaseInvoice = GenerationUtil.generatePurchaseInvoice(
                companyService.findAll(parameterMap).stream().findAny().get(),
                counterpartyService.findAll(parameterMap).stream().findAny().get(),
                agreementService.findAll(parameterMap).stream().findAny().get(),
                currencyService.findAll(parameterMap).stream().findAny().get(),
                new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 4L),
                purchaseInvoiceGoods);

        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            purchaseInvoiceService.create(purchaseInvoice);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(4)
    @Test
    void shouldBeUpdatePurchaseInvoiceWhenPurchaseInvoiceIsExistInDB() {
        PurchaseInvoiceGood purchaseInvoiceGood = GenerationUtil.generatePurchaseInvoiceGood(
                nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                150, 2);
        PurchaseInvoice purchaseInvoice = purchaseInvoiceService.findAll(parameterMap).stream().findAny().get();
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = purchaseInvoice.getPurchaseInvoiceGoods();
        purchaseInvoiceGood.setPurchaseInvoice(purchaseInvoice);
        purchaseInvoiceGoods.add(purchaseInvoiceGood);
        purchaseInvoice.setPurchaseInvoiceGoods(purchaseInvoiceGoods);
        purchaseInvoiceService.update(purchaseInvoice);
        PurchaseInvoice purchaseInvoiceById = purchaseInvoiceService.findById(purchaseInvoice.getId()).get();

        Assertions.assertEquals(2, purchaseInvoiceById.getPurchaseInvoiceGoods().size());
    }

    @Order(5)
    @Test
    void shouldBeReturnExceptionWhenUpdateWhenPurchaseInvoiceGoodIsEmpty() {
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
        PurchaseInvoice purchaseInvoice = purchaseInvoiceService.findAll(parameterMap).stream().findAny().get();
        purchaseInvoice.setPurchaseInvoiceGoods(purchaseInvoiceGoods);
        PurchaseInvoice purchaseInvoiceById = purchaseInvoiceService.findById(purchaseInvoice.getId()).get();

        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            purchaseInvoiceService.update(purchaseInvoice);
        });
    }

    @Order(6)
    @Test
    void shouldBeReturnExceptionWhenUpdateWhenPurchaseInvoiceGoodContainSumZero() {
        PurchaseInvoice purchaseInvoice = purchaseInvoiceService.findAll(parameterMap).stream().findAny().get();
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = purchaseInvoice.getPurchaseInvoiceGoods();
        purchaseInvoiceGoods.forEach(purchaseInvoiceGood -> purchaseInvoiceGood.setSum(BigDecimal.valueOf(0)));
        purchaseInvoice.setPurchaseInvoiceGoods(purchaseInvoiceGoods);
        PurchaseInvoice purchaseInvoiceById = purchaseInvoiceService.findById(purchaseInvoice.getId()).get();

        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            purchaseInvoiceService.update(purchaseInvoice);
        });
    }

    @Order(7)
    @Test
    public void shouldBeDeletePurchaseInvoiceWhenPurchaseInvoiceIsExist() {
        Long id = purchaseInvoiceService.findAll(parameterMap).stream().findFirst().get().getId();
        purchaseInvoiceService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(8)
    @Test
    public void shouldBeReturnExceptionWhenDeletePurchaseInvoiceWhenPurchaseInvoiceIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            purchaseInvoiceService.delete(200L);
        });
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(9)
    @Test
    public void shouldBeConvertedSumByCurrencyWhenCurrencyIsDifferentFromAccountCurrency() {
        purchaseInvoiceService.findAll(parameterMap).forEach(purchaseInvoice -> purchaseInvoiceService.delete(purchaseInvoice.getId()));
        Company company = companyService.findAll(parameterMap).stream().findAny().get();
        Currency currency = currencyService.findAll(parameterMap).stream()
                .filter(currencyFilter -> !currencyFilter.getCode().equals(accountingCurrencyCode))
                .findAny().get();
        CurrencyRate currencyRate = currencyRateService.findByDateAndAndCurrencyId(new Date(), currency.getId()).get();
        List<PurchaseInvoiceGood> purchaseInvoiceGoods = new ArrayList<>();
        Nomenclature nomenclature = nomenclatureService.findAll(parameterMap).stream().findAny().get();
        PurchaseInvoiceGood purchaseInvoiceGood = GenerationUtil.generatePurchaseInvoiceGood(
                nomenclature,
                100, 2);
        purchaseInvoiceGoods.add(purchaseInvoiceGood);
        PurchaseInvoice purchaseInvoice = GenerationUtil.generatePurchaseInvoice(
                company,
                counterpartyService.findAll(parameterMap).stream().findAny().get(),
                agreementService.findAll(parameterMap).stream().findAny().get(),
                currency,
                new Date(),
                purchaseInvoiceGoods);
        purchaseInvoiceService.create(purchaseInvoice);
        List<StockOfGood> stockOfGoods = stockOfGoodService.getStockOfGoodByNomenclatureIdAndCompanyId(nomenclature.getId(), company.getId(), new Date());

        Assertions.assertEquals(1, stockOfGoods.size());

        StockOfGood stockOfGood = stockOfGoods.get(0);

        Assertions.assertEquals(purchaseInvoiceGood.getPrice().floatValue()
                        * purchaseInvoiceGood.getQuantity().floatValue()
                        * currencyRate.getRate().floatValue()
                        / currencyRate.getFrequencyRate().floatValue()
                , stockOfGood.getCost().floatValue());
        Assertions.assertEquals(2, stockOfGood.getQuantity().floatValue());
        verifyItemsWhenItemsIsNotEmpty(1);
    }

    @Order(10)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        purchaseInvoiceService.findAll(parameterMap).forEach(purchaseInvoice -> purchaseInvoiceService.delete(purchaseInvoice.getId()));
        agreementService.findAll(parameterMap).forEach(agreement -> agreementService.delete(agreement.getId()));
        companyService.findAll(parameterMap).forEach(company -> companyService.delete(company.getId()));
        counterpartyService.findAll(parameterMap).forEach(counterparty -> counterpartyService.delete(counterparty.getId()));
        nomenclatureService.findAll(parameterMap).forEach(nomenclature -> nomenclatureService.delete(nomenclature.getId()));
        currencyRateService.findAll(parameterMap).forEach(currencyRate -> currencyRateService.delete(currencyRate.getId()));
        currencyService.findAll(parameterMap).forEach(currency -> currencyService.delete(currency.getId()));

        Assertions.assertEquals(0, purchaseInvoiceService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<PurchaseInvoice> items = purchaseInvoiceService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
