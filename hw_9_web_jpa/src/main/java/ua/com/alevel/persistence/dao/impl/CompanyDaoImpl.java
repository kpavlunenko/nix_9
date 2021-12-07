package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.CompanyDao;
import ua.com.alevel.persistence.entity.Company;

import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {

    private final SessionFactory sessionFactory;

    public CompanyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Company entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Company entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = sessionFactory.getCurrentSession().createQuery("delete from Company company where company.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("employee modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(company.id) from Company company where company.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Company findById(Long id) {
        return sessionFactory.getCurrentSession().find(Company.class, id);
    }

    @Override
    public List<Company> findAll(Map<String, String[]> parameterMap) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
        Root<Company> from = criteriaQuery.from(Company.class);
        if (parameterMap.get("order") != null) {
            if (parameterMap.get("order")[0].equals("desc")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(from.get(parameterMap.get("sort")[0])));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(from.get(parameterMap.get("sort")[0])));
            }
        }
        int page = 0;
        int size = 10;
        if (parameterMap.get("currentPage") != null) {
            size = Integer.parseInt(parameterMap.get("sizeOfPage")[0]);
            page = Integer.parseInt(parameterMap.get("currentPage")[0]);
        }

        List<Company> companies = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        return companies;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(c.id) from Company c");
        return (Long) query.getSingleResult();
    }
}
