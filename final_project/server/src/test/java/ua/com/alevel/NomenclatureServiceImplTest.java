package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.Nomenclature;
import ua.com.alevel.service.NomenclatureService;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class NomenclatureServiceImplTest {

    @Autowired
    private NomenclatureService nomenclatureService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Nomenclature nomenclature = GenerationUtil.generateNomenclature(GenerationUtil.NAME_NOMENCLATURE + i);
            nomenclatureService.create(nomenclature);
        }

        Assertions.assertEquals(ITEMS_SIZE, nomenclatureService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateNomenclatureWhenNameIsNotEmpty() {
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setName("New nomenclature");
        nomenclature.setProduct(true);
        nomenclatureService.create(nomenclature);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedNomenclatureWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Nomenclature nomenclature = new Nomenclature();
            nomenclature.setName("");
            nomenclature.setProduct(true);
            nomenclatureService.create(nomenclature);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateNomenclatureWhenNomenclatureExistInDB() {
        Nomenclature nomenclature = nomenclatureService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_NOMENCLATURE + GenerationUtil.NAME_NOMENCLATURE;
        nomenclature.setName(newName);
        nomenclatureService.update(nomenclature);
        Nomenclature nomenclatureById = nomenclatureService.findById(nomenclature.getId()).get();

        Assertions.assertEquals(nomenclatureById.getName(), newName);

    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateNomenclatureWhenNomenclatureNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Nomenclature nomenclature = nomenclatureService.findAll(parameterMap).stream().findAny().get();
            nomenclature.setName("");
            nomenclatureService.update(nomenclature);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteNomenclatureWhenNomenclatureIsExist() {
        Long id = nomenclatureService.findAll(parameterMap).stream().findFirst().get().getId();
        nomenclatureService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeleteNomenclatureWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            nomenclatureService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        nomenclatureService.findAll(parameterMap).forEach(nomenclature -> nomenclatureService.delete(nomenclature.getId()));

        Assertions.assertEquals(0, nomenclatureService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Nomenclature> items = nomenclatureService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
