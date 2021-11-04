package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.impl.CompanyServiceImpl;
import ua.com.alevel.service.impl.CustomerAgreementServiceImpl;
import ua.com.alevel.service.impl.CustomerServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerAgreementServiceImplTest {

    private final static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
    private final static CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    private final static CustomerAgreementServiceImpl customerAgreementServiceImpl = new CustomerAgreementServiceImpl();
    private final static int CUSTOMER_AGREEMENTS_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < CUSTOMER_AGREEMENTS_SIZE; i++) {
            Company company = GenerationUtil.generateCompany(GenerationUtil.NAME_COMPANY + i);
            companyServiceImpl.create(company);
            Customer customer = GenerationUtil.generateCustomer(GenerationUtil.NAME_CUSTOMER + i);
            customerServiceImpl.create(customer);
            CustomerAgreement customerAgreement = GenerationUtil.generateCustomerAgreement(GenerationUtil.NAME_CUSTOMER_AGREEMENT + i, company, customer);
            customerAgreementServiceImpl.create(customerAgreement);
        }

        Assertions.assertEquals(CUSTOMER_AGREEMENTS_SIZE, customerAgreementServiceImpl.findAll().length);
    }

    @Order(1)
    @Test
    public void shouldBeCreateAgreementCustomerWhenAllFieldsIsEmpty() {
        CustomerAgreement customerAgreement = new CustomerAgreement();
        customerAgreement.setName(null);
        customerAgreement.setCompany(null);
        customerAgreement.setCustomer(null);
        customerAgreementServiceImpl.create(customerAgreement);
        verifyCustomerAgreementArrayWhenCustomerAgreementArrayIsNotEmpty(CUSTOMER_AGREEMENTS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeFindCustomerAgreementByIdWhenCustomerAgreementExistInDB() {
        String id = getRandomIdOfCustomerAgreements();
        Assertions.assertDoesNotThrow(() -> {
            customerAgreementServiceImpl.findById(id);
        });

        verifyCustomerAgreementArrayWhenCustomerAgreementArrayIsNotEmpty(CUSTOMER_AGREEMENTS_SIZE + 1);
    }

    @Order(3)
    @Test
    public void shouldBeUpdateCustomerAgreementWhenCustomerAgreementExistInDB() {
        String id = getRandomIdOfCustomerAgreements();
        CustomerAgreement customerAgreement = customerAgreementServiceImpl.findById(id);
        String newName = GenerationUtil.NAME_CUSTOMER_AGREEMENT + GenerationUtil.NAME_CUSTOMER_AGREEMENT;
        customerAgreement.setName(newName);
        Company company = companyServiceImpl.findById(getRandomIdOfCompanies());
        customerAgreement.setCompany(company);
        Customer customer = customerServiceImpl.findById(getRandomIdOfCustomers());
        customerAgreement.setCustomer(customer);
        customerAgreementServiceImpl.update(customerAgreement);

        Assertions.assertEquals(customerAgreement.getName(), newName);
        Assertions.assertEquals(customerAgreement.getCompany().getId(), company.getId());
        Assertions.assertEquals(customerAgreement.getCustomer().getId(), customer.getId());
        verifyCustomerAgreementArrayWhenCustomerAgreementArrayIsNotEmpty(CUSTOMER_AGREEMENTS_SIZE + 1);
    }

    @Order(4)
    @Test
    public void shouldBeDeleteCustomerAgreementWhenCustomerAgreementIsExist() {
        String id = getRandomIdOfCustomerAgreements();
        customerAgreementServiceImpl.delete(id);
        verifyCustomerAgreementArrayWhenCustomerAgreementArrayIsNotEmpty(CUSTOMER_AGREEMENTS_SIZE);
    }

    private String getRandomIdOfCustomerAgreements() {
        return customerAgreementServiceImpl.findAll()[0].getId();
    }

    private String getRandomIdOfCustomers() {
        return customerServiceImpl.findAll()[1].getId();
    }

    private String getRandomIdOfCompanies() {
        return companyServiceImpl.findAll()[1].getId();
    }

    private void verifyCustomerAgreementArrayWhenCustomerAgreementArrayIsNotEmpty(int size) {
        CustomerAgreement[] customerAgreements = customerAgreementServiceImpl.findAll();

        Assertions.assertTrue(customerAgreements.length != 0);
        Assertions.assertEquals(size, customerAgreementServiceImpl.findAll().length);
    }
}
