package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.BusinessDirection;
import ua.com.alevel.persistence.entity.directory.Company;
import ua.com.alevel.persistence.type.CompanyType;
import ua.com.alevel.service.BusinessDirectionService;
import ua.com.alevel.service.CompanyService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BusinessDirectionServiceImplTest {

    @Autowired
    private BusinessDirectionService businessDirectionService;

    @Autowired
    private CompanyService companyService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            BusinessDirection businessDirection = GenerationUtil.generateBusinessDirection(GenerationUtil.NAME_BUSINESS_DIRECTION + i);
            businessDirectionService.create(businessDirection);
        }

        Assertions.assertEquals(ITEMS_SIZE, businessDirectionService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateBusinessDirectionWhenNameIsNotEmpty() {
        BusinessDirection businessDirection = new BusinessDirection();
        businessDirection.setName("New business direction");
        businessDirectionService.create(businessDirection);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedBusinessDirectionWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            BusinessDirection businessDirection = new BusinessDirection();
            businessDirectionService.create(businessDirection);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateBusinessDirectionWhenBusinessDirectionExistInDB() {
        BusinessDirection businessDirection = businessDirectionService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_BUSINESS_DIRECTION + GenerationUtil.NAME_BUSINESS_DIRECTION;
        businessDirection.setName(newName);
        businessDirectionService.update(businessDirection);
        BusinessDirection businessDirectionById = businessDirectionService.findById(businessDirection.getId()).get();

        Assertions.assertEquals(businessDirectionById.getName(), newName);

        Company company = new Company();
        company.setCompanyType(CompanyType.CORP);
        company.setName("company");
        companyService.create(company);
        Set<Company> companies = new HashSet<>();
        companies.add(company);
        businessDirection.setCompanies(companies);
        businessDirectionService.update(businessDirection);

        businessDirectionById = businessDirectionService.findById(businessDirection.getId()).get();

        Assertions.assertEquals(businessDirectionById.getCompanies().size(), companies.size());

    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateBusinessDirectionWhenBusinessDirectionNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            BusinessDirection businessDirection = businessDirectionService.findAll(parameterMap).stream().findAny().get();
            businessDirection.setName("");
            businessDirectionService.update(businessDirection);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteBusinessDirectionWhenBusinessDirectionIsExist() {
        Long id = businessDirectionService.findAll(parameterMap).stream().findFirst().get().getId();
        businessDirectionService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeleteBusinessDirectionWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            businessDirectionService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        businessDirectionService.findAll(parameterMap).forEach(businessDirection -> businessDirectionService.delete(businessDirection.getId()));
        companyService.findAll(parameterMap).forEach(company -> companyService.delete(company.getId()));

        Assertions.assertEquals(0, businessDirectionService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<BusinessDirection> items = businessDirectionService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
