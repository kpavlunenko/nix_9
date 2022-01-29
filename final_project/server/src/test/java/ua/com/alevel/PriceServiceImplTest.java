package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.Nomenclature;
import ua.com.alevel.persistence.entity.directory.PriceType;
import ua.com.alevel.persistence.entity.register.Price;
import ua.com.alevel.service.NomenclatureService;
import ua.com.alevel.service.PriceService;
import ua.com.alevel.service.PriceTypeService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PriceServiceImplTest {

    @Autowired
    private PriceTypeService priceTypeService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private NomenclatureService nomenclatureService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        PriceType priceType = GenerationUtil.generatePriceType(GenerationUtil.NAME_PRICE_TYPE);
        priceTypeService.create(priceType);

        Nomenclature nomenclature = GenerationUtil.generateNomenclature(GenerationUtil.NAME_NOMENCLATURE);
        nomenclatureService.create(nomenclature);

        for (int i = 0; i < ITEMS_SIZE; i++) {
            Price price = GenerationUtil.generatePrice(priceType,
                    new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * (5L + i))
                    , nomenclature,
                    100 * (i + 1));
            priceService.create(price);
        }

        Assertions.assertEquals(ITEMS_SIZE, priceService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreatePriceWhenPriceIsNotEmpty() {
        Price price = GenerationUtil.generatePrice(
                priceTypeService.findAll(parameterMap).stream().findAny().get(),
                new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 25L)
                , nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                100);
        priceService.create(price);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedPriceWhenPriceIsZero() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Price price = GenerationUtil.generatePrice(
                    priceTypeService.findAll(parameterMap).stream().findAny().get(),
                    new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 26L)
                    , nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                    0);
            priceService.create(price);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeReturnExceptionWhenCreatedPriceWhenPriceIsAlreadyExist() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Price price = GenerationUtil.generatePrice(
                    priceTypeService.findAll(parameterMap).stream().findAny().get(),
                    new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 25L)
                    , nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                    120);
            priceService.create(price);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(4)
    @Test
    void shouldBeUpdatePriceWhenPriceExistInDB() {
        Price price = priceService.findAll(parameterMap).stream().findAny().get();
        price.setPrice(BigDecimal.valueOf(500));
        priceService.update(price);
        Price priceById = priceService.findById(price.getId()).get();

        Assertions.assertEquals(priceById.getPrice().floatValue(), Float.valueOf(500));
    }

    @Order(5)
    @Test
    void shouldBeReturnExceptionWhenUpdatePriceWhenPriceValueIsZero() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Price price = priceService.findAll(parameterMap).stream().findAny().get();
            price.setPrice(BigDecimal.ZERO);
            priceService.update(price);
        });
    }

    @Order(6)
    @Test
    public void shouldBeDeletePriceWhenPriceIsExist() {
        Long id = priceService.findAll(parameterMap).stream().findFirst().get().getId();
        priceService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeReturnExceptionWhenDeletePriceWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            priceService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(8)
    @Test
    public void shouldBeReturnActualPriceWhenManyPricesIsExist() {
        Price price = GenerationUtil.generatePrice(
                priceTypeService.findAll(parameterMap).stream().findAny().get(),
                new Date()
                , nomenclatureService.findAll(parameterMap).stream().findAny().get(),
                1950);
        priceService.create(price);
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("date", new String[]{String.valueOf(System.currentTimeMillis())});
        parameterMap.put("priceType", new String[]{price.getPriceType().getId().toString()});
        parameterMap.put("nomenclature", new String[]{price.getNomenclature().getId().toString()});
        Price actualPrice = priceService.getNomenclaturePrice(parameterMap).get();

        Assertions.assertEquals(actualPrice.getPrice().floatValue(), Float.valueOf(1950));
    }

    @Order(9)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        priceService.findAll(parameterMap).forEach(price -> priceService.delete(price.getId()));
        priceTypeService.findAll(parameterMap).forEach(priceType -> priceTypeService.delete(priceType.getId()));
        nomenclatureService.findAll(parameterMap).forEach(nomenclature -> nomenclatureService.delete(nomenclature.getId()));

        Assertions.assertEquals(0, priceService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Price> items = priceService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
