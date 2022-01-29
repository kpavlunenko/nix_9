package ua.com.alevel;

import ua.com.alevel.persistence.entity.directory.*;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.persistence.entity.register.Price;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.type.AgreementType;
import ua.com.alevel.persistence.type.CompanyType;
import ua.com.alevel.persistence.type.CounterpartyType;
import ua.com.alevel.persistence.type.RoleType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public final class GenerationUtil {

    public static final String NAME_COMPANY = "test company";
    public static final String NAME_COUNTERPARTY = "test counterparty";
    public static final String NAME_AGREEMENT = "test agreement";
    public static final String NAME_BUSINESS_DIRECTION = "test business direction";
    public static final String NAME_CURRENCY = "test currency";
    public static final String NAME_NOMENCLATURE = "test nomenclature";
    public static final String NAME_PRICE_TYPE = "test price type";

    private GenerationUtil() {
    }

    public static PurchaseInvoice generatePurchaseInvoice(
            Company company, Counterparty counterparty, Agreement agreement,
            Currency currency, Date date, List<PurchaseInvoiceGood> purchaseInvoiceGoods) {
        PurchaseInvoice purchaseInvoice = new PurchaseInvoice();
        purchaseInvoice.setCompany(company);
        purchaseInvoice.setCounterparty(counterparty);
        purchaseInvoice.setAgreement(agreement);
        purchaseInvoice.setCurrency(currency);
        purchaseInvoice.setDate(date);
        purchaseInvoiceGoods.forEach(purchaseInvoiceGood -> purchaseInvoiceGood.setPurchaseInvoice(purchaseInvoice));
        purchaseInvoice.setPurchaseInvoiceGoods(purchaseInvoiceGoods);
        return purchaseInvoice;
    }

    public static PurchaseInvoiceGood generatePurchaseInvoiceGood(Nomenclature nomenclature, float price, float quantity) {
        PurchaseInvoiceGood purchaseInvoiceGood = new PurchaseInvoiceGood();
        purchaseInvoiceGood.setNomenclature(nomenclature);
        purchaseInvoiceGood.setPrice(BigDecimal.valueOf(price));
        purchaseInvoiceGood.setQuantity(BigDecimal.valueOf(quantity));
        purchaseInvoiceGood.setSum(BigDecimal.valueOf(quantity * price));
        return purchaseInvoiceGood;
    }

    public static Price generatePrice(PriceType priceType, Date date, Nomenclature nomenclature, float priceValue) {
        Price price = new Price();
        price.setPriceType(priceType);
        price.setDate(date);
        price.setNomenclature(nomenclature);
        price.setPrice(BigDecimal.valueOf(priceValue));
        return price;
    }

    public static PriceType generatePriceType(String name) {
        PriceType priceType = new PriceType();
        priceType.setName(name);
        return priceType;
    }

    public static Personal generatePersonal(String firstName, String lastName, String email, String password) {
        Personal personal = new Personal();
        personal.setBirthDay(new Date());
        personal.setRoleType(RoleType.ROLE_SALES_MANAGER);
        personal.setEnabled(true);
        personal.setLastName(lastName);
        personal.setFirstName(firstName);
        personal.setEmail(email);
        personal.setPassword(password);
        return personal;
    }

    public static Nomenclature generateNomenclature(String name) {
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setName(name);
        nomenclature.setProduct(true);
        nomenclature.setService(false);
        return nomenclature;
    }

    public static CurrencyRate generateCurrencyRate(Currency currency, Date date, float rate) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrency(currency);
        currencyRate.setDate(date);
        currencyRate.setRate(BigDecimal.valueOf(rate));
        currencyRate.setFrequencyRate(1);
        return currencyRate;
    }

    public static Currency generateCurrency(String name, String code) {
        Currency currency = new Currency();
        currency.setName(name);
        currency.setCode(code);
        return currency;
    }

    public static BusinessDirection generateBusinessDirection(String name) {
        BusinessDirection businessDirection = new BusinessDirection();
        businessDirection.setName(name);
        return businessDirection;
    }

    public static Company generateCompany(String name) {
        Company company = new Company();
        company.setName(name);
        company.setCompanyType(CompanyType.CORP);
        return company;
    }

    public static Counterparty generateCounterparty(String name, String inn, CounterpartyType counterpartyType) {
        Counterparty counterparty = new Counterparty();
        counterparty.setName(name);
        counterparty.setInn(inn);
        counterparty.setCounterpartyType(counterpartyType);
        return counterparty;
    }

    public static Agreement generateAgreement(String name, Company company, Counterparty counterparty) {
        Agreement agreement = new Agreement();
        agreement.setName(name);
        agreement.setCompany(company);
        agreement.setCounterparty(counterparty);
        if (counterparty.getCounterpartyType() == CounterpartyType.CLIENT) {
            agreement.setAgreementType(AgreementType.CLIENT_AGREEMENT);
        } else {
            agreement.setAgreementType(AgreementType.SUPPLIER_AGREEMENT);
        }
        return agreement;
    }
}
