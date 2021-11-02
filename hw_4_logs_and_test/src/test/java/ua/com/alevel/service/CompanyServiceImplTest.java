package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Company;
import ua.com.alevel.service.impl.CompanyServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyServiceImplTest {

    private final static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
    private final static int COMPANIES_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < COMPANIES_SIZE; i++) {
            Company company = CompanyGenerationUtil.generateCompany(CompanyGenerationUtil.NAME + i);
            companyServiceImpl.create(company);
        }

        Assertions.assertEquals(COMPANIES_SIZE, companyServiceImpl.findAll().length);
    }

    @Order(1)
    @Test
    public void shouldBeCreateCompanyWhenNameIsEmpty() {
        Company company = new Company();
        company.setName(null);
        companyServiceImpl.create(company);
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(11);
    }

    @Order(2)
    @Test
    public void shouldBeReturnAllCompaniesWhenCompanyWasCreated() {
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(11);
    }

    private void verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(int size) {
        Company[] companies = companyServiceImpl.findAll();

        Assertions.assertTrue(companies.length != 0);
        Assertions.assertEquals(size, companyServiceImpl.findAll().length);
    }
}
