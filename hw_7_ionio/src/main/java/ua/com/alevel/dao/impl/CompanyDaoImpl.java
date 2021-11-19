package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.CompanyDao;
import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.db.impl.CompanyListDBImpl;
import ua.com.alevel.entity.Company;

import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    private final CompanyDB instanceDB = CompanyListDBImpl.getInstance();

    @Override
    public void create(Company entity) {
        instanceDB.create(entity);
    }

    @Override
    public void update(Company entity) {
        instanceDB.update(entity);
    }

    @Override
    public void delete(String id) {
        instanceDB.delete(id);
    }

    @Override
    public Company findById(String id) {
        return instanceDB.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return instanceDB.findAll();
    }
}
