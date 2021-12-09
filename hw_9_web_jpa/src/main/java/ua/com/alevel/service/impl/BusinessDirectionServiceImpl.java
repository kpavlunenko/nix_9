package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.BusinessDirectionDao;
import ua.com.alevel.persistence.entity.BusinessDirection;
import ua.com.alevel.service.BusinessDirectionService;

import java.util.List;
import java.util.Map;

@Service
public class BusinessDirectionServiceImpl implements BusinessDirectionService {

    private final BusinessDirectionDao businessDirectionDao;

    public BusinessDirectionServiceImpl(BusinessDirectionDao businessDirectionDao) {
        this.businessDirectionDao = businessDirectionDao;
    }

    @Override
    public void create(BusinessDirection entity) {
        checkInputDataOnValid(entity);
        businessDirectionDao.create(entity);
    }

    @Override
    public void update(BusinessDirection entity) {
        if (!businessDirectionDao.existById(entity.getId())) {
            throw new EntityNotFoundException("business direction not found");
        }
        checkInputDataOnValid(entity);
        businessDirectionDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!businessDirectionDao.existById(id)) {
            throw new EntityNotFoundException("business direction not found");
        }
        businessDirectionDao.delete(id);
    }

    @Override
    public BusinessDirection findById(Long id) {
        if (!businessDirectionDao.existById(id)) {
            throw new EntityNotFoundException("business direction not found");
        }
        return businessDirectionDao.findById(id);
    }

    @Override
    public List<BusinessDirection> findAll(Map<String, String[]> parameterMap) {
        List<BusinessDirection> businessDirections = businessDirectionDao.findAll(parameterMap);
        return businessDirections;
    }

    @Override
    public long count(Map<String, String[]> parameterMap) {
        return businessDirectionDao.count(parameterMap);
    }


    private void checkInputDataOnValid(BusinessDirection entity) {
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
