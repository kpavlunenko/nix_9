package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.BankOperation;
import ua.com.alevel.persistence.repository.BankOperationRepository;
import ua.com.alevel.persistence.type.BankOperationType;
import ua.com.alevel.persistence.type.OperationType;
import ua.com.alevel.service.BankOperationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BankOperationServiceImpl implements BankOperationService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CrudRepositoryHelper<BankOperation, BankOperationRepository> repositoryHelper;
    private final BankOperationRepository bankOperationRepository;

    public BankOperationServiceImpl(CrudRepositoryHelper<BankOperation, BankOperationRepository> repositoryHelper, BankOperationRepository bankOperationRepository) {
        this.repositoryHelper = repositoryHelper;
        this.bankOperationRepository = bankOperationRepository;
    }

    @Override
    @Transactional
    public void create(BankOperation entity) {
        checkInputDataOnValid(entity);
        if (entity.getOperationType() == OperationType.EXPENSE) {
            entity.setAmount(entity.getAmount().negate());
        }
        LOGGER_INFO.info("object: BankOperation; stage: start; operation: create");
        repositoryHelper.create(bankOperationRepository, entity);
        LOGGER_INFO.info("object: BankOperation; stage: finish; operation: create; id = " + entity.getId());
        if (entity.getBankOperationType() == BankOperationType.OUTCOME_TRANSFER) {
            BankOperation bankOperation = new BankOperation();
            bankOperation.setBankAccount(entity.getRecipientBankAccount());
            bankOperation.setOperationType(OperationType.INCOME);
            bankOperation.setBankOperationType(BankOperationType.INCOME_TRANSFER);
            bankOperation.setCategory(entity.getCategory());
            bankOperation.setAmount(entity.getAmount().negate());
            LOGGER_INFO.info("object: BankOperation; stage: start; operation: create");
            repositoryHelper.create(bankOperationRepository, bankOperation);
            LOGGER_INFO.info("object: BankOperation; stage: finish; operation: create; id = " + entity.getId());
        }
    }

    @Override
    @Transactional
    public void update(BankOperation entity) {
        checkInputDataOnValid(entity);
        LOGGER_INFO.info("object: BankOperation; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(bankOperationRepository, entity);
        LOGGER_INFO.info("object: BankOperation; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOGGER_WARN.warn("object: BankOperation; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(bankOperationRepository, id);
        LOGGER_WARN.warn("object: BankOperation; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BankOperation> findById(Long id) {
        return repositoryHelper.findById(bankOperationRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankOperation> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(bankOperationRepository, parameterMap, BankOperation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(bankOperationRepository, parameterMap, BankOperation.class);
    }

    private void checkInputDataOnValid(BankOperation entity) {
        if (entity.getAmount() == BigDecimal.ZERO) {
            LOGGER_ERROR.error("object: BankOperation; operation: update/create; id = " + entity.getId() + "; problem = " + "amount is empty");
            throw new IncorrectInputData("amount is empty");
        }
        if (entity.getOperationType() == OperationType.EXPENSE) {
           BigDecimal balance = bankOperationRepository.findBalanceByBankAccount(entity.getBankAccount().getId());
           if (balance == null) {
               balance = BigDecimal.ZERO;
           }
           if (balance.compareTo(entity.getAmount()) == -1) {
               LOGGER_ERROR.error("object: BankOperation; operation: update/create; id = " + entity.getId() + "; problem = " + "on your balance not enough money. your balance " + balance);
               throw new IncorrectInputData("on your balance not enough money. your balance " + balance);
           }
        }
    }

}
