package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.CounterpartyDao;
import ua.com.alevel.persistence.entity.Counterparty;

import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class CounterpartyDaoImpl implements CounterpartyDao {

    private final SessionFactory sessionFactory;

    public CounterpartyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Counterparty entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Counterparty entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = sessionFactory.getCurrentSession().createQuery("delete from Counterparty counterparty where counterparty.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("counterparty modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(counterparty.id) from Counterparty counterparty where counterparty.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Counterparty findById(Long id) {
        return sessionFactory.getCurrentSession().find(Counterparty.class, id);
    }

    @Override
    public List<Counterparty> findAll(Map<String, String[]> parameterMap) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Counterparty> criteriaQuery = criteriaBuilder.createQuery(Counterparty.class);
        Root<Counterparty> from = criteriaQuery.from(Counterparty.class);
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

        List<Counterparty> counterparties = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        return counterparties;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(counterparty.id) from Counterparty counterparty");
        return (Long) query.getSingleResult();
    }
}
