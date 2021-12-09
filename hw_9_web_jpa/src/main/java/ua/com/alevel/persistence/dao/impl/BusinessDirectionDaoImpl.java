package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.BusinessDirectionDao;
import ua.com.alevel.persistence.entity.BusinessDirection;

import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class BusinessDirectionDaoImpl implements BusinessDirectionDao {

    private final SessionFactory sessionFactory;

    public BusinessDirectionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(BusinessDirection entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(BusinessDirection entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = sessionFactory.getCurrentSession().createQuery("delete from BusinessDirection businessDirection where businessDirection.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("business direction modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(businessDirection.id) from BusinessDirection businessDirection where businessDirection.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public BusinessDirection findById(Long id) {
        return sessionFactory.getCurrentSession().find(BusinessDirection.class, id);
    }

    @Override
    public List<BusinessDirection> findAll(Map<String, String[]> parameterMap) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<BusinessDirection> criteriaQuery = criteriaBuilder.createQuery(BusinessDirection.class);
        Root<BusinessDirection> from = criteriaQuery.from(BusinessDirection.class);
        if (parameterMap.get("order") != null) {
            if (parameterMap.get("order")[0].equals("desc")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(from.get(parameterMap.get("sort")[0])));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(from.get(parameterMap.get("sort")[0])));
            }
        }
        int page = 0;
        int size;
        if (parameterMap.get("currentPage") != null) {
            size = Integer.parseInt(parameterMap.get("sizeOfPage")[0]);
            page = Integer.parseInt(parameterMap.get("currentPage")[0]);
        } else {
            size = (int)count(parameterMap);
        }

        List<BusinessDirection> businessDirections = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        return businessDirections;
    }

    @Override
    public long count(Map<String, String[]> parameterMap) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(businessDirections.id) from BusinessDirection businessDirections");
        return (Long) query.getSingleResult();
    }

}
