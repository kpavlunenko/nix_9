package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.BankOperationDao;
import ua.com.alevel.persistence.entity.BankOperation;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class BankOperationDaoImpl implements BankOperationDao {

    private final SessionFactory sessionFactory;

    public BankOperationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<BankOperation> findOperationByBankAccountId(Long id) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<BankOperation> criteriaQuery = criteriaBuilder.createQuery(BankOperation.class);
        Root<BankOperation> root = criteriaQuery.from(BankOperation.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
        criteriaQuery.where(criteriaBuilder.equal(root.get("bankAccount").get("id"), id));
        int page = 0;
        int size;
        size = (int) count(id);

        List<BankOperation> BankOperations = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        return BankOperations;
    }

    public long count(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(b.id) from BankOperation b where b.bankAccount.id = :id")
                .setParameter("id", id);;
        return (Long) query.getSingleResult();
    }
}
