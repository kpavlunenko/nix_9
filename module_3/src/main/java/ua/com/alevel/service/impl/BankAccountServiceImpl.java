package ua.com.alevel.service.impl;

import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.BankAccount;
import ua.com.alevel.persistence.entity.BankOperation;
import ua.com.alevel.persistence.repository.BankAccountRepository;
import ua.com.alevel.persistence.repository.BankOperationRepository;
import ua.com.alevel.service.BankAccountService;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CrudRepositoryHelper<BankAccount, BankAccountRepository> repositoryHelper;
    private final BankAccountRepository bankAccountRepository;
    private final BankOperationRepository bankOperationRepository;

    public BankAccountServiceImpl(CrudRepositoryHelper<BankAccount, BankAccountRepository> repositoryHelper,
                                  BankAccountRepository bankAccountRepository,
                                  BankOperationRepository bankOperationRepository) {
        this.repositoryHelper = repositoryHelper;
        this.bankAccountRepository = bankAccountRepository;
        this.bankOperationRepository = bankOperationRepository;
    }

    @Override
    @Transactional
    public void create(BankAccount entity) {
        checkInputDataOnValid(entity);
        LOGGER_INFO.info("object: BankAccount; stage: start; operation: create");
        repositoryHelper.create(bankAccountRepository, entity);
        LOGGER_INFO.info("object: BankAccount; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(BankAccount entity) {
        checkInputDataOnValid(entity);
        LOGGER_INFO.info("object: BankAccount; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(bankAccountRepository, entity);
        LOGGER_INFO.info("object: BankAccount; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOGGER_WARN.warn("object: BankOperation; stage: start; operation: delete; BankAccountId = " + id);
        bankOperationRepository.deleteAllByBankAccount_Id(id);
        LOGGER_WARN.warn("object: BankOperation; stage: finish; operation: delete; BankAccountId = " + id);
        LOGGER_WARN.warn("object: BankAccount; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(bankAccountRepository, id);
        LOGGER_WARN.warn("object: BankAccount; stage: finish; operation: delete; id = " + id);
    }
    @Override
    @Transactional
    public void deleteAllByUser_Id(Long id) {
        List<BankAccount> bankAccounts = bankAccountRepository.findAllByUser_Id(id);
        LOGGER_WARN.warn("object: BankOperation; stage: start; operation: delete; UserId = " + id);
        bankAccounts.stream().forEach(bankAccount -> bankOperationRepository.deleteAllByBankAccount_Id(bankAccount.getId()));
        LOGGER_WARN.warn("object: BankOperation; stage: finish; operation: delete; UserId = " + id);
        LOGGER_WARN.warn("object: BankAccount; stage: start; operation: delete; UserId = " + id);
        bankAccountRepository.deleteAllByUser_Id(id);
        LOGGER_WARN.warn("object: BankAccount; stage: finish; operation: delete; UserId = " + id);
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

    @Override
    @Transactional(readOnly = true)
    public String getAccountStatement(Long id) {
        String filePath = "./files";
        BankAccount bankAccount = findById(id).get();
        String fileName = "account_" + bankAccount.getIban() + ".csv";
        List<String[]> csvFile = new ArrayList<>();
        String[] header = new String[5];
        header[0] = "id";
        header[1] = "date";
        header[2] = "amount";
        header[3] = "account";
        header[4] = "category";
        csvFile.add(header);

        List<BankOperation> bankOperations = bankOperationRepository.findAllByBankAccount_Id(id);
        bankOperations.forEach(bankOperation -> csvFile.add(new String[] {
                bankOperation.getId().toString(),
                bankOperation.getCreated().toString(),
                bankOperation.getAmount().toString(),
                bankOperation.getBankAccount().getIban(),
                bankOperation.getCategory().getName()}));

        try {
            if (Files.notExists(Path.of(filePath))) {
                Files.createDirectories(Path.of(filePath));
            }
            String allPath = filePath + "\\" + fileName;
            if (Files.notExists(Path.of(allPath))) {
                Files.createFile(Path.of(allPath));
            }
        } catch (IOException e) {
            LOGGER_ERROR.error("Cannot find or create file" + fileName);
            throw new RuntimeException(e.getCause());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath + "\\" + fileName))) {
            writer.writeAll(csvFile);
        } catch (IOException e) {
            LOGGER_ERROR.error("Cannot write to file {}" + fileName);
            throw new RuntimeException(e.getCause());
        }
        return fileName;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal findBalanceByBankAccount(Long id) {
        return bankOperationRepository.findBalanceByBankAccount(id);
    }

    private void checkInputDataOnValid(BankAccount entity) {
        if (!nameIsValid(entity.getName())) {
            LOGGER_ERROR.error("object: BankAccount; operation: update/create; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
        if (entity.getIban().length() != 29) {
            LOGGER_ERROR.error("object: BankAccount; operation: update/create; id = " + entity.getId() + "; problem = " + "IBAN should contain 29 chars");
            throw new IncorrectInputData("IBAN should contain 29 chars");
        }
        try {
            BankAccount bankAccount = bankAccountRepository.findByIban(entity.getIban()).get();
            if (bankAccount.getId() != entity.getId()) {
                LOGGER_ERROR.error("object: BankAccount; operation: update/create; id = " + entity.getId() + "; problem = " + "account with input IBAN already exist");
                throw new IncorrectInputData("account with input IBAN already exist");
            }
        } catch (NoSuchElementException e) {

        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
