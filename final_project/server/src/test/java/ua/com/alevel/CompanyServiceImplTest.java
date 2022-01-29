package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.Agreement;
import ua.com.alevel.persistence.entity.directory.Company;
import ua.com.alevel.persistence.entity.directory.Counterparty;
import ua.com.alevel.persistence.type.CounterpartyType;
import ua.com.alevel.service.AgreementService;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.CounterpartyService;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class CompanyServiceImplTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CounterpartyService counterpartyService;

    @Autowired
    private AgreementService agreementService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Company company = GenerationUtil.generateCompany(GenerationUtil.NAME_COMPANY + i);
            companyService.create(company);
        }

        Assertions.assertEquals(ITEMS_SIZE, companyService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateCompanyWhenNameIsNotEmpty() {
        Company company = new Company();
        company.setName("New company");
        companyService.create(company);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedCompanyWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Company company = new Company();
            companyService.create(company);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateCompanyWhenCompanyExistInDB() {
        Company company = companyService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_COMPANY + GenerationUtil.NAME_COMPANY;
        company.setName(newName);
        companyService.update(company);
        Company companyById = companyService.findById(company.getId()).get();

        Assertions.assertEquals(companyById.getName(), newName);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateCompanyWhenCompanyNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Company company = companyService.findAll(parameterMap).stream().findAny().get();
            company.setName("");
            companyService.update(company);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteCompanyWhenCompanyAgreementDoesNotExist() {
        Long id = companyService.findAll(parameterMap).stream().findFirst().get().getId();
        companyService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeNotDeleteCompanyWhenCompanyAgreementIsExist() {
        Company company = companyService.findAll(parameterMap).stream().findAny().get();
        Counterparty counterparty = GenerationUtil.generateCounterparty(GenerationUtil.NAME_COUNTERPARTY, "111111111111", CounterpartyType.CLIENT);
        counterpartyService.create(counterparty);
        Agreement agreement = GenerationUtil.generateAgreement(GenerationUtil.NAME_AGREEMENT, company, counterparty);
        agreementService.create(agreement);
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            companyService.delete(company.getId());
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        agreementService.findAll(parameterMap).forEach(agreement -> agreementService.delete(agreement.getId()));
        companyService.findAll(parameterMap).forEach(company -> companyService.delete(company.getId()));
        counterpartyService.findAll(parameterMap).forEach(counterparty -> counterpartyService.delete(counterparty.getId()));

        Assertions.assertEquals(0, companyService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Company> items = companyService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
