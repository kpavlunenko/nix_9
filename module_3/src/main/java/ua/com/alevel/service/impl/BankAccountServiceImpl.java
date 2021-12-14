package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.BankAccount;
import ua.com.alevel.persistence.repository.BankAccountRepository;
import ua.com.alevel.service.BankAccountService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final CrudRepositoryHelper<BankAccount, BankAccountRepository> repositoryHelper;
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(CrudRepositoryHelper<BankAccount, BankAccountRepository> repositoryHelper, BankAccountRepository bankAccountRepository) {
        this.repositoryHelper = repositoryHelper;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public void create(BankAccount entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(bankAccountRepository, entity);
    }

    @Override
    @Transactional
    public void update(BankAccount entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(bankAccountRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryHelper.delete(bankAccountRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BankAccount> findById(Long id) {
        return repositoryHelper.findById(bankAccountRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankAccount> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(bankAccountRepository, parameterMap, BankAccount.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(bankAccountRepository, parameterMap, BankAccount.class);
    }

    private void checkInputDataOnValid(BankAccount entity) {
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
        if (entity.getIban().length() != 29) {
            throw new IncorrectInputData("IBAN should contain 29 chars");
        }
        try {
            BankAccount bankAccount = bankAccountRepository.findByIban(entity.getIban()).get();
            if (bankAccount.getId() != entity.getId()) {
                throw new IncorrectInputData("account with input IBAN already exist");
            }
        } catch (NoSuchElementException e) {

        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
