package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.AgreementDao;
import ua.com.alevel.persistence.entity.Agreement;
import ua.com.alevel.service.AgreementService;

import java.util.List;
import java.util.Map;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementDao agreementDao;

    public AgreementServiceImpl(AgreementDao agreementDao) {
        this.agreementDao = agreementDao;
    }

    @Override
    public void create(Agreement entity) {
        checkInputDataOnValid(entity);
        agreementDao.create(entity);
    }

    @Override
    public void update(Agreement entity) {
        if (!agreementDao.existById(entity.getId())) {
            throw new EntityNotFoundException("agreement not found");
        }
        checkInputDataOnValid(entity);
        agreementDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!agreementDao.existById(id)) {
            throw new EntityNotFoundException("agreement not found");
        }
        agreementDao.delete(id);
    }

    @Override
    public Agreement findById(Long id) {
        if (!agreementDao.existById(id)) {
            throw new EntityNotFoundException("agreement not found");
        }
        return agreementDao.findById(id);
    }

    @Override
    public List<Agreement> findAll(Map<String, String[]> parameterMap) {
        List<Agreement> agreements = agreementDao.findAll(parameterMap);
        return agreements;
    }

    @Override
    public long count(Map<String, String[]> parameterMap) {
        return agreementDao.count(parameterMap);
    }

    private void checkInputDataOnValid(Agreement entity) {
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
