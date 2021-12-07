package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.AgreementDao;
import ua.com.alevel.persistence.entity.Agreement;


import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class AgreementDaoImpl implements AgreementDao {

    private final SessionFactory sessionFactory;

    public AgreementDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Agreement entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Agreement entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = sessionFactory.getCurrentSession().createQuery("delete from Agreement agreement where agreement.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("agreement modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(agreement.id) from Agreement agreement where agreement.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Agreement findById(Long id) {
        return sessionFactory.getCurrentSession().find(Agreement.class, id);
    }

    @Override
    public List<Agreement> findAll(Map<String, String[]> parameterMap) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Agreement> criteriaQuery = criteriaBuilder.createQuery(Agreement.class);
        Root<Agreement> from = criteriaQuery.from(Agreement.class);
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

        List<Agreement> agreements = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        return agreements;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(agreement.id) from Agreement agreement");
        return (Long) query.getSingleResult();
    }
}
