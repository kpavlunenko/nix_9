package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.IncorrectInputData;
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
    private final CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelper;


    public PersonalServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            PersonalRepository personalRepository, CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personalRepository = personalRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Personal entity) {
        if (StringUtils.isEmpty(entity.getEmail()) || StringUtils.isEmpty(entity.getPassword())) {
            throw new IncorrectInputData("login or password is empty");
        }
        if (personalRepository.existsByEmail(entity.getEmail())) {
            throw new EntityExistException("this personal is exist");
        }
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.create(personalRepository, entity);
    }

    @Override
    public void update(Personal entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Personal> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Personal> findAll(Map<String, String[]> parameterMap) {
        return null;
    }

    @Override
    public long count(Map<String, String[]> parameterMap) {
        return 0;
    }
}
