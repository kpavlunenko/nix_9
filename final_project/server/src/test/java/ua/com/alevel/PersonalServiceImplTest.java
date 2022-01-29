package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.PersonalService;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PersonalServiceImplTest {

    @Autowired
    private PersonalService personalService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Personal personal = GenerationUtil.generatePersonal("first name" + i, "last name" + i, "test" + i + "@gmail.com", "password" + i);
            personalService.create(personal);
        }

        Assertions.assertEquals(ITEMS_SIZE, personalService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreatePersonalWhenAllFieldsIsNotEmpty() {
        Personal personal = GenerationUtil.generatePersonal("first name", "last name", "test@gmail.com", "password");
        personalService.create(personal);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedPersonalWhenEmailOrPasswordIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Personal personal = GenerationUtil.generatePersonal("first name", "last name", "", "password");
            personalService.create(personal);
        });

        exception = assertThrows(IncorrectInputData.class, () -> {
            Personal personal = GenerationUtil.generatePersonal("first name", "last name", "test10@gmail.com", "");
            personalService.create(personal);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeReturnExceptionWhenCreatedPersonalWhenEmailIsAlreadyExist() {
        Exception exception = assertThrows(EntityExistException.class, () -> {
            Personal personal = GenerationUtil.generatePersonal("first name", "last name", "test@gmail.com", "password");
            personalService.create(personal);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(4)
    @Test
    void shouldBeUpdatePersonalWhenPersonalExistInDB() {
        Personal personal = personalService.findAll(parameterMap).stream().findAny().get();
        personal.setEnabled(false);
        personalService.update(personal);
        Personal personalById = personalService.findByEmail(personal.getEmail()).get();

        Assertions.assertEquals(personalById.getEnabled(), false);
    }

    @Order(5)
    @Test
    void shouldBeReturnExceptionWhenUpdatePersonalWhenPersonalPasswordIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Personal personal = personalService.findAll(parameterMap).stream().findAny().get();
            personal.setPassword("");
            personalService.update(personal);
        });
    }

    @Order(6)
    @Test
    public void shouldBeDeletePersonalWhenPersonalIsExist() {
        Long id = personalService.findAll(parameterMap).stream().findFirst().get().getId();
        personalService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeReturnExceptionWhenDeletePersonalWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            personalService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(8)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        personalService.findAll(parameterMap).forEach(personal -> personalService.delete(personal.getId()));

        Assertions.assertEquals(0, personalService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Personal> items = personalService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
