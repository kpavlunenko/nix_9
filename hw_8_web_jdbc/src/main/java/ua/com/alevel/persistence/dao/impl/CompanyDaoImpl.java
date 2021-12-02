package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.CompanyDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.type.CompanyType;
import ua.com.alevel.util.JpaQueryUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private final JpaConfig jpaConfig;

    public CompanyDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Company entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryCreate("companies", 5))) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.isDeletionMark());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getCompanyType().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Company entity) {
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("company_type");
        columns.add("updated");
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryUpdateById("companies", columns) + entity.getId())) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCompanyType().name());
            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryCompanyBusinessDirectionDeleteByCompanyId(id))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryDeleteById("companies") + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCountById("companies") + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("count");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Company findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryFindById("companies") + id)) {
            while (resultSet.next()) {
                return initCompanyByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return new Company();
    }

    @Override
    public ResponseDataTable<Company> findAll(RequestDataTable request) {
        List<Company> companies = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCompanyFindAll(request))) {
            while (resultSet.next()) {
                Company company = initCompanyByResultSet(resultSet);
                companies.add(company);
                otherParamMap.put(company.getId(), resultSet.getLong("counterparties"));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        ResponseDataTable<Company> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(companies);
        responseDataTable.setOtherParamMap(otherParamMap);
        return responseDataTable;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryFindAll("companies"))) {
            while (resultSet.next()) {
                companies.add(initCompanyByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return companies;
    }


    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCount("companies"))) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Company initCompanyByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String companyType = resultSet.getString("company_type");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean deletionMark = resultSet.getBoolean("deletion_mark");

        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setCompanyType(CompanyType.valueOf(companyType));
        company.setCreated(created);
        company.setUpdated(updated);
        company.setDeletionMark(deletionMark);

        return company;
    }

}
