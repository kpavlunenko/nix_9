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
public class CounterpartyServiceImplTest {

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
            Counterparty counterparty = GenerationUtil.generateCounterparty(GenerationUtil.NAME_COUNTERPARTY + i, "11111111111" + i, CounterpartyType.CLIENT);
            counterpartyService.create(counterparty);
        }

        Assertions.assertEquals(ITEMS_SIZE, counterpartyService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateCounterpartyWhenNameIsNotEmpty() {
        Counterparty counterparty = new Counterparty();
        counterparty.setName("New counterparty");
        counterparty.setCounterpartyType(CounterpartyType.CLIENT);
        counterparty.setInn("123333333333");
        counterpartyService.create(counterparty);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedCounterpartyWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Counterparty counterparty = new Counterparty();
            counterparty.setCounterpartyType(CounterpartyType.CLIENT);
            counterparty.setInn("123333333333");
            counterpartyService.create(counterparty);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateCounterpartyWhenCounterpartyExistInDB() {
        Counterparty counterparty = counterpartyService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_COUNTERPARTY + GenerationUtil.NAME_COUNTERPARTY;
        counterparty.setName(newName);
        counterpartyService.update(counterparty);
        Counterparty counterpartyById = counterpartyService.findById(counterparty.getId()).get();

        Assertions.assertEquals(counterpartyById.getName(), newName);

        counterparty.setCounterpartyType(CounterpartyType.SUPPLIER);
        counterpartyService.update(counterparty);
        counterpartyById = counterpartyService.findById(counterparty.getId()).get();

        Assertions.assertEquals(counterpartyById.getCounterpartyType(), CounterpartyType.SUPPLIER);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateCounterpartyWhenCounterpartyNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Counterparty counterparty = counterpartyService.findAll(parameterMap).stream().findAny().get();
            counterparty.setName("");
            counterpartyService.update(counterparty);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteCounterpartyWhenAgreementDoesNotExist() {
        Long id = counterpartyService.findAll(parameterMap).stream().findFirst().get().getId();
        counterpartyService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeNotDeleteCounterpartyWhenAgreementIsExist() {
        Company company = GenerationUtil.generateCompany(GenerationUtil.NAME_COMPANY);
        companyService.create(company);
        Counterparty counterparty = counterpartyService.findAll(parameterMap).stream().findAny().get();
        Agreement agreement = GenerationUtil.generateAgreement(GenerationUtil.NAME_AGREEMENT, company, counterparty);
        agreementService.create(agreement);
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            counterpartyService.delete(counterparty.getId());
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
        List<Counterparty> items = counterpartyService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
