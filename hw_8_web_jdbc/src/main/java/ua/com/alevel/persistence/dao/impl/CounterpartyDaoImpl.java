package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.CounterpartyDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.type.CounterpartyType;
import ua.com.alevel.util.JpaQueryUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class CounterpartyDaoImpl implements CounterpartyDao {

    private final JpaConfig jpaConfig;

    public CounterpartyDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Counterparty entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryCreate("counterparties", 6))) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.isDeletionMark());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getCounterpartyType().name());
            preparedStatement.setString(6, entity.getInn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Counterparty entity) {
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("counterparty_type");
        columns.add("inn");
        columns.add("updated");
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryUpdateById("counterparties", columns) + entity.getId())) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCounterpartyType().name());
            preparedStatement.setString(3, entity.getInn());
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryDeleteById("counterparties") + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCountById("counterparties") + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("count");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Counterparty findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryFindById("counterparties") + id)) {
            while (resultSet.next()) {
                return initCounterpartyByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return new Counterparty();
    }

    @Override
    public ResponseDataTable<Counterparty> findAll(RequestDataTable request) {
        List<Counterparty> counterparties = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCounterpartyFindAll(request))) {
            while (resultSet.next()) {
                Counterparty counterparty = initCounterpartyByResultSet(resultSet);
                counterparties.add(counterparty);
                otherParamMap.put(counterparty.getId(), resultSet.getLong("agreements"));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        ResponseDataTable<Counterparty> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(counterparties);
        responseDataTable.setOtherParamMap(otherParamMap);
        return responseDataTable;
    }

    @Override
    public ResponseDataTable<Counterparty> findAllByCompany(RequestDataTable request, long companyId) {
        List<Counterparty> counterparties = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        String sqlQuery = "";
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCounterpartiesFindAllByCompany(request, companyId))) {
            while (resultSet.next()) {
                Counterparty counterparty = initCounterpartyByResultSet(resultSet);
                counterparties.add(counterparty);
                otherParamMap.put(counterparty.getId(), resultSet.getLong("agreements"));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        ResponseDataTable<Counterparty> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(counterparties);
        responseDataTable.setOtherParamMap(otherParamMap);
        return responseDataTable;
    }

    @Override
    public List<Counterparty> findAll() {
        List<Counterparty> counterparties = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryFindAll("counterparties"))) {
            while (resultSet.next()) {
                counterparties.add(initCounterpartyByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return counterparties;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCount("counterparties"))) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long countByCompany(long companyId) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCounterpartiesCountByCompany(companyId))) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Counterparty initCounterpartyByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String counterpartyType = resultSet.getString("counterparty_type");
        String inn = resultSet.getString("inn");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean deletionMark = resultSet.getBoolean("deletion_mark");

        Counterparty counterparty = new Counterparty();
        counterparty.setId(id);
        counterparty.setName(name);
        counterparty.setCounterpartyType(CounterpartyType.valueOf(counterpartyType));
        counterparty.setInn(inn);
        counterparty.setCreated(created);
        counterparty.setUpdated(updated);
        counterparty.setDeletionMark(deletionMark);

        return counterparty;
    }
}
