package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.Currency;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.service.CurrencyRateService;
import ua.com.alevel.service.CurrencyService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CurrencyRateServiceImplTest {

    @Autowired
    private CurrencyRateService currencyRateService;

    @Autowired
    private CurrencyService currencyService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Currency currency = GenerationUtil.generateCurrency(GenerationUtil.NAME_CURRENCY + i, "01" + i);
            currencyService.create(currency);
            CurrencyRate currencyRate = GenerationUtil.generateCurrencyRate(currency, new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 5L), i * 5);
            currencyRateService.create(currencyRate);
        }

        Assertions.assertEquals(ITEMS_SIZE, currencyRateService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateCurrencyRateWhenAllFieldsIsNotEmpty() {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrency(currencyService.findAll(parameterMap).stream().findAny().get());
        currencyRate.setFrequencyRate(1);
        currencyRate.setRate(BigDecimal.ONE);
        currencyRate.setDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 12L));
        currencyRateService.create(currencyRate);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedCurrencyRateWhenRateIsEmptyOrRateIsExistOnThisDate() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCurrency(currencyService.findAll(parameterMap).stream().findAny().get());
            currencyRate.setFrequencyRate(1);
            currencyRate.setDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 10L));
            currencyRateService.create(currencyRate);
        });

        exception = assertThrows(IncorrectInputData.class, () -> {
            CurrencyRate currencyRateInDB = currencyRateService.findAll(parameterMap).stream().findAny().get();
            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCurrency(currencyRateInDB.getCurrency());
            currencyRate.setFrequencyRate(currencyRateInDB.getFrequencyRate());
            currencyRate.setDate(currencyRateInDB.getDate());
            currencyRate.setRate(BigDecimal.ONE);
            currencyRateService.create(currencyRate);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateCurrencyRateWhenCurrencyRateExistInDB() {
        CurrencyRate currencyRate = currencyRateService.findAll(parameterMap).stream().findAny().get();
        currencyRate.setRate(BigDecimal.TEN);
        currencyRateService.update(currencyRate);
        CurrencyRate currencyRateById = currencyRateService.findById(currencyRate.getId()).get();

        Assertions.assertEquals(currencyRateById.getRate().floatValue(), BigDecimal.TEN.floatValue());

    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateCurrencyRateWhenCurrencyRateIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            CurrencyRate currencyRate = currencyRateService.findAll(parameterMap).stream().findAny().get();
            currencyRate.setRate(BigDecimal.ZERO);
            currencyRateService.update(currencyRate);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteCurrencyRateWhenCurrencyIsExist() {
        Long id = currencyRateService.findAll(parameterMap).stream().findFirst().get().getId();
        currencyRateService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeleteCurrencyRateWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            currencyRateService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeReturnActualRateOnDateWhenExistManyRatesByCurrency() {
        Currency currency = currencyService.findAll(parameterMap).stream().findAny().get();
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrency(currency);
        currencyRate.setFrequencyRate(1);
        currencyRate.setDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 2L));
        currencyRate.setRate(BigDecimal.valueOf(25));
        currencyRateService.create(currencyRate);

        currencyRate = new CurrencyRate();
        currencyRate.setCurrency(currency);
        currencyRate.setFrequencyRate(1);
        currencyRate.setDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 1L));
        currencyRate.setRate(BigDecimal.valueOf(30));
        currencyRateService.create(currencyRate);

        currencyRate = currencyRateService.findByDateAndAndCurrencyId(new Date(), currency.getId()).get();

        Assertions.assertEquals(currencyRate.getRate().floatValue(), Float.valueOf(30));
    }

    @Order(8)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        currencyRateService.findAll(parameterMap).forEach(currencyRate -> currencyRateService.delete(currencyRate.getId()));
        currencyService.findAll(parameterMap).forEach(currency -> currencyService.delete(currency.getId()));

        Assertions.assertEquals(0, currencyRateService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<CurrencyRate> items = currencyRateService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
