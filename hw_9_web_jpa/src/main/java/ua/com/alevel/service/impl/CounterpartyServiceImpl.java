package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.CounterpartyDao;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.service.CounterpartyService;

import java.util.List;
import java.util.Map;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyDao counterpartyDao;

    public CounterpartyServiceImpl(CounterpartyDao counterpartyDao) {
        this.counterpartyDao = counterpartyDao;
    }

    @Override
    public void create(Counterparty entity) {
        checkInputDataOnValid(entity);
        counterpartyDao.create(entity);
    }

    @Override
    public void update(Counterparty entity) {
        if (!counterpartyDao.existById(entity.getId())) {
            throw new EntityNotFoundException("counterparty not found");
        }
        checkInputDataOnValid(entity);
        counterpartyDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!counterpartyDao.existById(id)) {
            throw new EntityNotFoundException("counterparty not found");
        }
        counterpartyDao.delete(id);
    }

    @Override
    public Counterparty findById(Long id) {
        if (!counterpartyDao.existById(id)) {
            throw new EntityNotFoundException("counterparty not found");
        }
        return counterpartyDao.findById(id);
    }

    @Override
    public List<Counterparty> findAll(Map<String, String[]> parameterMap) {
        List<Counterparty> counterparties = counterpartyDao.findAll(parameterMap);
        return counterparties;
    }

    @Override
    public long count(Map<String, String[]> parameterMap) {
        return counterpartyDao.count(parameterMap);
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
