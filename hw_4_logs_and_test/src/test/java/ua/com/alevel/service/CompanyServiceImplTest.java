package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.impl.CompanyServiceImpl;
import ua.com.alevel.service.impl.CustomerAgreementServiceImpl;
import ua.com.alevel.service.impl.CustomerServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyServiceImplTest {

    private final static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
    private final static CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    private final static CustomerAgreementServiceImpl customerAgreementServiceImpl = new CustomerAgreementServiceImpl();
    private final static int COMPANIES_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < COMPANIES_SIZE; i++) {
            Company company = GenerationUtil.generateCompany(GenerationUtil.NAME_COMPANY + i);
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
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(COMPANIES_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeFindCompanyByIdWhenCompanyExistInDB() {
        String id = companyServiceImpl.findAll()[0].getId();
        Assertions.assertDoesNotThrow(() -> {
            companyServiceImpl.findById(id);
        });
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(COMPANIES_SIZE + 1);
    }

    @Order(3)
    @Test
    public void shouldBeUpdateCompanyWhenCompanyExistInDB() {
        String id = companyServiceImpl.findAll()[0].getId();
        Company company = new Company();
        company.setId(id);
        String newName = GenerationUtil.NAME_COMPANY + GenerationUtil.NAME_COMPANY;
        company.setName(newName);
        companyServiceImpl.update(company);
        Company companyById = companyServiceImpl.findById(id);

        Assertions.assertEquals(companyById.getName(), newName);
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(COMPANIES_SIZE + 1);
    }

    @Order(4)
    @Test
    public void shouldBeDeleteCompanyWhenCompanyAgreementDoesNotExist() {
        String id = companyServiceImpl.findAll()[0].getId();
        companyServiceImpl.delete(id);
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(COMPANIES_SIZE);
    }

    @Order(5)
    @Test
    public void shouldBeNotDeleteCompanyWhenCompanyAgreementIsExist() {
        String id = companyServiceImpl.findAll()[0].getId();
        Company company = companyServiceImpl.findById(id);
        Customer customer = GenerationUtil.generateCustomer(GenerationUtil.NAME_CUSTOMER);
        customerServiceImpl.create(customer);
        CustomerAgreement customerAgreement = GenerationUtil.generateCustomerAgreement(GenerationUtil.NAME_CUSTOMER_AGREEMENT, company, customer);
        customerAgreementServiceImpl.create(customerAgreement);
        companyServiceImpl.delete(id);
        verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(COMPANIES_SIZE);
    }

    private void verifyCompanyArrayWhenCompaniesArrayIsNotEmpty(int size) {
        Company[] companies = companyServiceImpl.findAll();

        Assertions.assertTrue(companies.length != 0);
        Assertions.assertEquals(size, companyServiceImpl.findAll().length);
    }
}
