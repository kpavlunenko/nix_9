package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.Currency;
import ua.com.alevel.service.CurrencyService;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CurrencyServiceImplTest {

    @Autowired
    private CurrencyService currencyService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Currency currency = GenerationUtil.generateCurrency(GenerationUtil.NAME_CURRENCY + i, "01" + i);
            currencyService.create(currency);
        }

        Assertions.assertEquals(ITEMS_SIZE, currencyService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateCurrencyWhenNameIsNotEmpty() {
        Currency currency = new Currency();
        currency.setName("New currency");
        currency.setCode("980");
        currencyService.create(currency);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedCurrencyWhenNameOrCodeIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Currency currency = new Currency();
            currency.setName("new currency");
            currencyService.create(currency);
        });

        exception = assertThrows(IncorrectInputData.class, () -> {
            Currency currency = new Currency();
            currency.setCode("990");
            currencyService.create(currency);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateCurrencyWhenCurrencyExistInDB() {
        Currency currency = currencyService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_CURRENCY + GenerationUtil.NAME_CURRENCY;
        currency.setName(newName);
        currencyService.update(currency);
        Currency currencyById = currencyService.findById(currency.getId()).get();

        Assertions.assertEquals(currencyById.getName(), newName);

        String newCode = currency.getCode() + currency.getCode();
        currency.setCode(newCode);
        currencyService.update(currency);
        currencyById = currencyService.findById(currency.getId()).get();

        Assertions.assertEquals(currencyById.getCode(), newCode);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateCurrencyWhenCurrencyNameOrCodeIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Currency currency = currencyService.findAll(parameterMap).stream().findAny().get();
            currency.setName("");
            currencyService.update(currency);
        });

        exception = assertThrows(IncorrectInputData.class, () -> {
            Currency currency = currencyService.findAll(parameterMap).stream().findAny().get();
            currency.setCode("");
            currencyService.update(currency);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteCurrencyWhenCurrencyIsExist() {
        Long id = currencyService.findAll(parameterMap).stream().findFirst().get().getId();
        currencyService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeleteCurrencyWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            currencyService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        currencyService.findAll(parameterMap).forEach(currency -> currencyService.delete(currency.getId()));

        Assertions.assertEquals(0, currencyService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Currency> items = currencyService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
