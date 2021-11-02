package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Company;
import ua.com.alevel.service.impl.CompanyServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyServiceImplTest {

    private final CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();

    @Order(1)
    @Test
    public void shouldBeCreateCompanyWhenNameIsEmpty() {
        Company company = new Company();
        company.setName(null);
        companyServiceImpl.create(company);
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty();
    }

    @Order(2)
    @Test
    public void shouldBeReturnAllCompaniesWhenCompanyWasCreated() {
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty();
    }

    private void verifyCompanyArrayWhenCompaniesArrayIsNotEmpty() {
        boolean testIsTrue = false;
        Company[] companies = companyServiceImpl.findAll();
        for (int i = 0; i < companies.length; i++) {
            if (companies[i] != null) {
                testIsTrue = true;
            }
        }

        Assertions.assertTrue(testIsTrue);
    }
}
