package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.persistence.repository.CounterpartyRepository;
import ua.com.alevel.service.AgreementService;
import ua.com.alevel.service.CounterpartyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CrudRepositoryHelper<Counterparty, CounterpartyRepository> repositoryHelper;
    private final CounterpartyRepository counterpartyRepository;
    private final AgreementService agreementService;

    public CounterpartyServiceImpl(CrudRepositoryHelper<Counterparty, CounterpartyRepository> repositoryHelper,
                                   CounterpartyRepository counterpartyRepository,
                                   AgreementService agreementService) {
        this.repositoryHelper = repositoryHelper;
        this.counterpartyRepository = counterpartyRepository;
        this.agreementService = agreementService;
    }

    @Override
    @Transactional()
    public void create(Counterparty entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(counterpartyRepository, entity);
    }

    @Override
    @Transactional()
    public void update(Counterparty entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(counterpartyRepository, entity);
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        Map<String, String[]> params = new HashMap<>();
        params.put("counterparty", new String[]{String.valueOf(id)});
        long countAgreements =  agreementService.count(params);
        if (countAgreements != 0) {
            throw new EntityNotFoundException("With this counterparty exist " + countAgreements + " agreements you can not delete this counterparty");
        }
        repositoryHelper.delete(counterpartyRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Counterparty> findById(Long id) {
        return repositoryHelper.findById(counterpartyRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Counterparty> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(counterpartyRepository, parameterMap, Counterparty.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(counterpartyRepository, parameterMap, Counterparty.class);
    }

    private void checkInputDataOnValid(Counterparty entity) {
        if (!innIsValid(entity.getInn())) {
            throw new IncorrectInputData("inn is not valid");
        }
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean innIsValid(String inn) {
        return inn.matches("[0-9]+") && inn.length() == 12;
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
