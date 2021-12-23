package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Currency;
import ua.com.alevel.persistence.repository.CurrencyRateRepository;
import ua.com.alevel.persistence.repository.CurrencyRepository;
import ua.com.alevel.service.CurrencyService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CrudRepositoryHelper<Currency, CurrencyRepository> repositoryHelper;
    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;

    public CurrencyServiceImpl(CrudRepositoryHelper<Currency, CurrencyRepository> repositoryHelper, CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository) {
        this.repositoryHelper = repositoryHelper;
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
    }

    @Override
    @Transactional
    public void create(Currency entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(currencyRepository, entity);
    }

    @Override
    @Transactional
    public void update(Currency entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(currencyRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        currencyRateRepository.deleteAllByCurrency_Id(id);
        repositoryHelper.delete(currencyRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Currency> findById(Long id) {
        return repositoryHelper.findById(currencyRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Currency> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(currencyRepository, parameterMap, Currency.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(currencyRepository, parameterMap, Currency.class);
    }

    private void checkInputDataOnValid(Currency entity) {
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
        if (!codeIsValid(entity.getCode())) {
            throw new IncorrectInputData("code is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }

    private boolean codeIsValid(String code) {
        return StringUtils.isNotEmpty(code) && StringUtils.isNotBlank(code);
    }
}
