package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.PriceType;
import ua.com.alevel.service.PriceTypeService;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PriceTypeServiceImplTest {

    @Autowired
    private PriceTypeService priceTypeService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            PriceType priceType = GenerationUtil.generatePriceType(GenerationUtil.NAME_PRICE_TYPE + i);
            priceTypeService.create(priceType);
        }

        Assertions.assertEquals(ITEMS_SIZE, priceTypeService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreatePriceTypeWhenNameIsNotEmpty() {
        PriceType priceType = new PriceType();
        priceType.setName("New price type");
        priceTypeService.create(priceType);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedPriceTypeWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            PriceType priceType = new PriceType();
            priceType.setName("");
            priceTypeService.create(priceType);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdatePriceTypeWhenPriceTypeExistInDB() {
        PriceType priceType = priceTypeService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_PRICE_TYPE + GenerationUtil.NAME_PRICE_TYPE;
        priceType.setName(newName);
        priceTypeService.update(priceType);
        PriceType priceTypeById = priceTypeService.findById(priceType.getId()).get();

        Assertions.assertEquals(priceTypeById.getName(), newName);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdatePriceTypeWhenPriceTypeNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            PriceType priceType = priceTypeService.findAll(parameterMap).stream().findAny().get();
            priceType.setName("");
            priceTypeService.update(priceType);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeletePriceTypeWhenPriceTypeIsExist() {
        Long id = priceTypeService.findAll(parameterMap).stream().findFirst().get().getId();
        priceTypeService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeletePriceTypeWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            priceTypeService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        priceTypeService.findAll(parameterMap).forEach(priceType -> priceTypeService.delete(priceType.getId()));

        Assertions.assertEquals(0, priceTypeService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<PriceType> items = priceTypeService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
