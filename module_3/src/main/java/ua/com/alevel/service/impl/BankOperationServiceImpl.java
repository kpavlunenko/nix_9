package ua.com.alevel.service.impl;

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
        repositoryHelper.create(bankOperationRepository, entity);
        if (entity.getBankOperationType() == BankOperationType.OUTCOME_TRANSFER) {
            BankOperation bankOperation = new BankOperation();
            bankOperation.setBankAccount(entity.getRecipientBankAccount());
            bankOperation.setOperationType(OperationType.INCOME);
            bankOperation.setBankOperationType(BankOperationType.INCOME_TRANSFER);
            bankOperation.setCategory(entity.getCategory());
            bankOperation.setAmount(entity.getAmount());
            repositoryHelper.create(bankOperationRepository, bankOperation);
        }
    }

    @Override
    @Transactional
    public void update(BankOperation entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(bankOperationRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryHelper.delete(bankOperationRepository, id);
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
            throw new IncorrectInputData("amount is empty");
        }
    }

}
