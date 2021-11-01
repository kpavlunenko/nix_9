package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CompanyDao;
import ua.com.alevel.dao.impl.CompanyDaoImpl;
import ua.com.alevel.entity.Company;
import ua.com.alevel.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public void create(Company entity) {
        companyDao.create(entity);
    }

    @Override
    public void update(Company entity) {
        companyDao.update(entity);
    }

    @Override
    public void delete(String id) {
        companyDao.delete(id);
    }

    @Override
    public Company findById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public Company[] findAll() {
        return companyDao.findAll();
    }
}
