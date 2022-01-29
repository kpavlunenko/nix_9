package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.entity.directory.Agreement;
import ua.com.alevel.persistence.entity.directory.Company;
import ua.com.alevel.persistence.entity.directory.Counterparty;
import ua.com.alevel.persistence.type.AgreementType;
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
public class AgreementServiceImplTest {

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
            Counterparty counterparty = GenerationUtil.generateCounterparty(GenerationUtil.NAME_COUNTERPARTY + 1, "11111111111" + i, CounterpartyType.CLIENT);
            counterpartyService.create(counterparty);
            Agreement agreement = GenerationUtil.generateAgreement(GenerationUtil.NAME_AGREEMENT + 1, company, counterparty);
            agreementService.create(agreement);
        }

        Assertions.assertEquals(ITEMS_SIZE, agreementService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateAgreementWhenNameIsNotEmpty() {
        Agreement agreement = new Agreement();
        agreement.setName("New agreement");
        agreement.setCompany(companyService.findAll(parameterMap).stream().findAny().get());
        agreement.setCounterparty(counterpartyService.findAll(parameterMap).stream().findAny().get());
        agreementService.create(agreement);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedAgreementWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Agreement agreement = GenerationUtil.generateAgreement(GenerationUtil.NAME_AGREEMENT + 1,
                    companyService.findAll(parameterMap).stream().findAny().get(),
                    counterpartyService.findAll(parameterMap).stream().findAny().get());
            agreement.setName("");
            agreementService.create(agreement);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateAgreementWhenAgreementExistInDB() {
        Agreement agreement = agreementService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_AGREEMENT + GenerationUtil.NAME_AGREEMENT;
        agreement.setName(newName);
        agreement.setAgreementType(AgreementType.CLIENT_AGREEMENT);
        agreementService.update(agreement);
        Agreement agreementById = agreementService.findById(agreement.getId()).get();

        Assertions.assertEquals(agreementById.getName(), newName);
        Assertions.assertEquals(agreementById.getAgreementType(), AgreementType.CLIENT_AGREEMENT);

        agreement.setAgreementType(AgreementType.SUPPLIER_AGREEMENT);
        agreementService.update(agreement);
        agreementById = agreementService.findById(agreement.getId()).get();

        Assertions.assertEquals(agreementById.getAgreementType(), AgreementType.SUPPLIER_AGREEMENT);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateAgreementWhenAgreementNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Agreement agreement = agreementService.findAll(parameterMap).stream().findAny().get();
            agreement.setName("");
            agreementService.update(agreement);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteAgreementWhenAgreementIsExist() {
        Agreement agreement = agreementService.findAll(parameterMap).stream().findAny().get();
        agreementService.delete(agreement.getId());
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeleteAgreementAndItDoesNotIsExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            agreementService.delete(100L);
        });
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        agreementService.findAll(parameterMap).forEach(agreement -> agreementService.delete(agreement.getId()));
        companyService.findAll(parameterMap).forEach(company -> companyService.delete(company.getId()));
        counterpartyService.findAll(parameterMap).forEach(counterparty -> counterpartyService.delete(counterparty.getId()));

        Assertions.assertEquals(0, agreementService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Agreement> items = agreementService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
