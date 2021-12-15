package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.BankAccountRepository;
import ua.com.alevel.persistence.repository.CategoryRepository;
import ua.com.alevel.persistence.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final CrudRepositoryHelper<User, UserRepository> repositoryHelper;
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    public UserServiceImpl(CrudRepositoryHelper<User, UserRepository> repositoryHelper, UserRepository userRepository, BankAccountRepository bankAccountRepository) {
        this.repositoryHelper = repositoryHelper;
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public void create(User entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(userRepository, entity);
    }

    @Override
    @Transactional
    public void update(User entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(userRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bankAccountRepository.deleteAllByUser_Id(id);
        repositoryHelper.delete(userRepository, id);
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
            throw new IncorrectInputData("first name is not valid");
        }
        if (!nameIsValid(entity.getLastName())) {
            throw new IncorrectInputData("last name is not valid");
        }
        if (!nameIsValid(entity.getPhone())) {
            throw new IncorrectInputData("phone is not valid");
        }
        if (!nameIsValid(entity.getEmail())) {
            throw new IncorrectInputData("email is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
