package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudTableRepositoryHelper;
import ua.com.alevel.persistence.entity.CurrencyRate;
import ua.com.alevel.persistence.repository.CurrencyRateRepository;
import ua.com.alevel.service.CurrencyRateService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CrudTableRepositoryHelper<CurrencyRate, CurrencyRateRepository> repositoryHelper;
    private final CurrencyRateRepository currencyRateRepository;

    public CurrencyRateServiceImpl(CrudTableRepositoryHelper<CurrencyRate, CurrencyRateRepository> repositoryHelper, CurrencyRateRepository currencyRateRepository) {
        this.repositoryHelper = repositoryHelper;
        this.currencyRateRepository = currencyRateRepository;
    }

    @Override
    @Transactional
    public void create(CurrencyRate entity) {
        repositoryHelper.create(currencyRateRepository, entity);
    }

    @Override
    @Transactional
    public void update(CurrencyRate entity) {
        repositoryHelper.update(currencyRateRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryHelper.delete(currencyRateRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyRate> findById(Long id) {
        return repositoryHelper.findById(currencyRateRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyRate> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(currencyRateRepository, parameterMap, CurrencyRate.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(currencyRateRepository, parameterMap, CurrencyRate.class);
    }
}
