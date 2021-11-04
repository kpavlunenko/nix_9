package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.impl.CompanyServiceImpl;
import ua.com.alevel.service.impl.CustomerAgreementServiceImpl;
import ua.com.alevel.service.impl.CustomerServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceImplTest {

    private final static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
    private final static CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    private final static CustomerAgreementServiceImpl customerAgreementServiceImpl = new CustomerAgreementServiceImpl();
    private final static int CUSTOMERS_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < CUSTOMERS_SIZE; i++) {
            Customer customer = GenerationUtil.generateCustomer(GenerationUtil.NAME_CUSTOMER + i);
            customerServiceImpl.create(customer);
        }

        Assertions.assertEquals(CUSTOMERS_SIZE, customerServiceImpl.findAll().length);
    }

    @Order(1)
    @Test
    public void shouldBeCreateCustomerWhenNameIsEmpty() {
        Customer customer = new Customer();
        customer.setName(null);
        customerServiceImpl.create(customer);
        verifyCustomerArrayWhenCustomersArrayIsNotEmpty(CUSTOMERS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeFindCustomerByIdWhenCustomerExistInDB() {
        String id = customerServiceImpl.findAll()[0].getId();

        Assertions.assertNotNull(customerServiceImpl.findById(id));
        Assertions.assertDoesNotThrow(() -> {
            customerServiceImpl.findById(id);
        });
        verifyCustomerArrayWhenCustomersArrayIsNotEmpty(CUSTOMERS_SIZE + 1);
    }

    @Order(3)
    @Test
    public void shouldBeUpdateCustomerWhenCustomerExistInDB() {
        String id = customerServiceImpl.findAll()[0].getId();
        Customer customer = new Customer();
        customer.setId(id);
        String newName = GenerationUtil.NAME_CUSTOMER + GenerationUtil.NAME_CUSTOMER;
        customer.setName(newName);
        customerServiceImpl.update(customer);
        Customer customerById = customerServiceImpl.findById(id);

        Assertions.assertEquals(customerById.getName(), newName);
        verifyCustomerArrayWhenCustomersArrayIsNotEmpty(CUSTOMERS_SIZE + 1);
    }

    @Order(4)
    @Test
    public void shouldBeDeleteCustomerWhenCustomerAgreementDoesNotExist() {
        String id = customerServiceImpl.findAll()[0].getId();
        customerServiceImpl.delete(id);
        verifyCustomerArrayWhenCustomersArrayIsNotEmpty(CUSTOMERS_SIZE);
    }

    @Order(5)
    @Test
    public void shouldBeNotDeleteCustomerWhenCustomerAgreementIsExist() {
        String id = customerServiceImpl.findAll()[0].getId();
        Customer customer = customerServiceImpl.findById(id);
        Company company = GenerationUtil.generateCompany(GenerationUtil.NAME_COMPANY);
        companyServiceImpl.create(company);
        CustomerAgreement customerAgreement = GenerationUtil.generateCustomerAgreement(GenerationUtil.NAME_CUSTOMER_AGREEMENT, company, customer);
        customerAgreementServiceImpl.create(customerAgreement);
        customerServiceImpl.delete(id);
        verifyCustomerArrayWhenCustomersArrayIsNotEmpty(CUSTOMERS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeEmptyArrayWhenDBIsClear() {
        GenerationUtil.clearDB();

        Assertions.assertEquals(0, customerServiceImpl.findAll().length);
    }

    private void verifyCustomerArrayWhenCustomersArrayIsNotEmpty(int size) {
        Customer[] customers = customerServiceImpl.findAll();

        Assertions.assertTrue(customers.length != 0);
        Assertions.assertEquals(size, customerServiceImpl.findAll().length);
    }
}
