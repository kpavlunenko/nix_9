package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BankAccount;

import java.math.BigDecimal;

public interface BankAccountService extends BaseService<BankAccount> {

    BigDecimal findBalanceByBankAccount(Long id);
    void deleteAllByUser_Id(Long id);
}
