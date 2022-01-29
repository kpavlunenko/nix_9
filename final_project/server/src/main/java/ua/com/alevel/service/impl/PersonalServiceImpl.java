package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.PersonalService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PersonalRepository personalRepository;
    private final CrudRepositoryHelper<Personal, PersonalRepository> repositoryHelper;
    private final LoggerService loggerService;

    public PersonalServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            PersonalRepository personalRepository, CrudRepositoryHelper<Personal, PersonalRepository> repositoryHelper, LoggerService loggerService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personalRepository = personalRepository;
        this.repositoryHelper = repositoryHelper;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(Personal entity) {
        if (StringUtils.isEmpty(entity.getEmail()) || StringUtils.isEmpty(entity.getPassword())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "login or password is empty");
            throw new IncorrectInputData("login or password is empty");
        }
        if (personalRepository.existsByEmail(entity.getEmail())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "this personal is exist");
            throw new EntityExistException("this personal is exist");
        }
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(personalRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(Personal entity) {
        if (StringUtils.isEmpty(entity.getPassword())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "password is empty");
            throw new IncorrectInputData("password is empty");
        }
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(personalRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, "object: " + Personal.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(personalRepository, id);
        loggerService.commit(LoggerLevel.INFO, "object: " + Personal.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Personal> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Personal> findByEmail(String email) {
        return personalRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personal> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(personalRepository, parameterMap, Personal.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(personalRepository, parameterMap, Personal.class);
    }
}
