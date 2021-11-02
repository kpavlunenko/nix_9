package ua.com.alevel.entity;

public class CustomerAgreement extends BaseEntity {

    private Customer customer;
    private Company company;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerAgreement() {
        super();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "CustomerAgreement{" +
                "id=" + super.getId() +
                ", name=" + name +
                ", company=" + company.getName() +
                ", customer='" + customer.getName() + '\'' +
                '}';
    }
}
