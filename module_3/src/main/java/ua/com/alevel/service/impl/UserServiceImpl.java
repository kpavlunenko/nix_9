package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.UserRepository;
import ua.com.alevel.service.BankAccountService;
import ua.com.alevel.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CrudRepositoryHelper<User, UserRepository> repositoryHelper;
    private final UserRepository userRepository;
    private final BankAccountService bankAccountService;

    public UserServiceImpl(CrudRepositoryHelper<User, UserRepository> repositoryHelper, UserRepository userRepository, BankAccountService bankAccountService) {
        this.repositoryHelper = repositoryHelper;
        this.userRepository = userRepository;
        this.bankAccountService = bankAccountService;
    }

    @Override
    @Transactional
    public void create(User entity) {
        checkInputDataOnValid(entity);
        LOGGER_INFO.info("object: User; stage: start; operation: create");
        repositoryHelper.create(userRepository, entity);
        LOGGER_INFO.info("object: User; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(User entity) {
        checkInputDataOnValid(entity);
        LOGGER_INFO.info("object: User; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(userRepository, entity);
        LOGGER_INFO.info("object: User; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOGGER_WARN.warn("object: BankAccount; stage: start; operation: delete; Userid = " + id);
        bankAccountService.deleteAllByUser_Id(id);
        LOGGER_WARN.warn("object: BankAccount; stage: finish; operation: delete; Userid = " + id);
        LOGGER_WARN.warn("object: User; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(userRepository, id);
        LOGGER_WARN.warn("object: User; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repositoryHelper.findById(userRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(userRepository, parameterMap, User.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(userRepository, parameterMap, User.class);
    }

    private void checkInputDataOnValid(User entity) {
        if (!nameIsValid(entity.getFirstName())) {
            LOGGER_ERROR.error("object: User; operation: update/create; id = " + entity.getId() + "; problem = " + "first name is not valid");
            throw new IncorrectInputData("first name is not valid");
        }
        if (!nameIsValid(entity.getLastName())) {
            LOGGER_ERROR.error("object: User; operation: update/create; id = " + entity.getId() + "; problem = " + "last name is not valid");
            throw new IncorrectInputData("last name is not valid");
        }
        if (!nameIsValid(entity.getPhone())) {
            LOGGER_ERROR.error("object: User; operation: update/create; id = " + entity.getId() + "; problem = " + "phone is not valid");
            throw new IncorrectInputData("phone is not valid");
        }
        if (!nameIsValid(entity.getEmail())) {
            LOGGER_ERROR.error("object: User; operation: update/create; id = " + entity.getId() + "; problem = " + "email is not valid");
            throw new IncorrectInputData("email is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
