package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.AgreementDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Agreement;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.type.AgreementType;
import ua.com.alevel.type.CompanyType;
import ua.com.alevel.type.CounterpartyType;
import ua.com.alevel.util.JpaQueryUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AgreementDaoImpl implements AgreementDao {

    private final JpaConfig jpaConfig;

    public AgreementDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Agreement entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryCreate("agreements", 7))) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.isDeletionMark());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getAgreementType().name());
            preparedStatement.setLong(6, entity.getCompany().getId());
            preparedStatement.setLong(7, entity.getCounterparty().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Agreement entity) {
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("agreement_type");
        columns.add("company_id");
        columns.add("counterparty_id");
        columns.add("updated");
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryUpdateById("agreements", columns) + entity.getId())) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAgreementType().name());
            preparedStatement.setLong(3, entity.getCompany().getId());
            preparedStatement.setLong(4, entity.getCounterparty().getId());
            preparedStatement.setTimestamp(5, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryDeleteById("agreements") + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCountById("agreements") + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("count");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Agreement findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryAgreementFindById() + id)) {
            while (resultSet.next()) {
                return initAgreementByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return new Agreement();
    }

    @Override
    public ResponseDataTable<Agreement> findAll(RequestDataTable request) {
        List<Agreement> agreements = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryAgreementFindAll(request))) {
            while (resultSet.next()) {
                agreements.add(initAgreementByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        ResponseDataTable<Agreement> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(agreements);
        return responseDataTable;
    }

    @Override
    public ResponseDataTable<Agreement> findAllByCounterparty(RequestDataTable request, long counterpartyId) {
        List<Agreement> agreements = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryAgreementFindAllByCounterparty(request, counterpartyId))) {
            while (resultSet.next()) {
                agreements.add(initAgreementByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        ResponseDataTable<Agreement> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(agreements);
        return responseDataTable;
    }

    @Override
    public List<Agreement> findAll() {
        List<Agreement> agreements = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryAgreementFindAll())) {
            while (resultSet.next()) {
                agreements.add(initAgreementByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return agreements;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCount("agreements"))) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long countByCounterparty(long counterpartyId) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryAgreementsCountByCounterparty(counterpartyId))) {
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
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryAgreementsCountByCompany(companyId))) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Agreement initAgreementByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("agreement.id");
        String name = resultSet.getString("agreement.name");
        String agreementType = resultSet.getString("agreement.agreement_type");
        Timestamp created = resultSet.getTimestamp("agreement.created");
        Timestamp updated = resultSet.getTimestamp("agreement.updated");
        Boolean deletionMark = resultSet.getBoolean("agreement.deletion_mark");

        Agreement agreement = new Agreement();
        agreement.setId(id);
        agreement.setName(name);
        agreement.setAgreementType(AgreementType.valueOf(agreementType));
        agreement.setCreated(created);
        agreement.setUpdated(updated);
        agreement.setDeletionMark(deletionMark);

        long companyId = resultSet.getLong("company.id");
        String companyName = resultSet.getString("company.name");
        String companyType = resultSet.getString("company.company_type");
        Timestamp companyCreated = resultSet.getTimestamp("company.created");
        Timestamp companyUpdated = resultSet.getTimestamp("company.updated");
        Boolean companyDeletionMark = resultSet.getBoolean("company.deletion_mark");

        Company company = new Company();
        company.setId(companyId);
        company.setName(companyName);
        company.setCompanyType(CompanyType.valueOf(companyType));
        company.setCreated(companyCreated);
        company.setUpdated(companyUpdated);
        company.setDeletionMark(companyDeletionMark);

        long counterpartyId = resultSet.getLong("counterparty.id");
        String counterpartyName = resultSet.getString("counterparty.name");
        String counterpartyType = resultSet.getString("counterparty.counterparty_type");
        String counterpartyInn = resultSet.getString("counterparty.inn");
        Timestamp counterpartyCreated = resultSet.getTimestamp("counterparty.created");
        Timestamp counterpartyUpdated = resultSet.getTimestamp("counterparty.updated");
        Boolean counterpartyDeletionMark = resultSet.getBoolean("counterparty.deletion_mark");

        Counterparty counterparty = new Counterparty();
        counterparty.setId(counterpartyId);
        counterparty.setName(counterpartyName);
        counterparty.setCounterpartyType(CounterpartyType.valueOf(counterpartyType));
        counterparty.setInn(counterpartyInn);
        counterparty.setCreated(counterpartyCreated);
        counterparty.setUpdated(counterpartyUpdated);
        counterparty.setDeletionMark(counterpartyDeletionMark);

        agreement.setCompany(company);
        agreement.setCounterparty(counterparty);
        return agreement;
    }
}
