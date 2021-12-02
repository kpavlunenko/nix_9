package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.BusinessDirectionDao;
import ua.com.alevel.persistence.dao.CompanyDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.BusinessDirection;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.util.JpaQueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BusinessDirectionDaoImpl implements BusinessDirectionDao {

    private final JpaConfig jpaConfig;
    private final CompanyDao companyDao;

    public BusinessDirectionDaoImpl(JpaConfig jpaConfig, CompanyDao companyDao) {
        this.jpaConfig = jpaConfig;
        this.companyDao = companyDao;
    }

    @Override
    public void create(BusinessDirection entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryCreate("business_directions", 4), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.isDeletionMark());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
                updateLinksManyToMany(entity);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(BusinessDirection entity) {
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("updated");
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryUpdateById("business_directions", columns) + entity.getId())) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.executeUpdate();
            updateLinksManyToMany(entity);
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void updateLinksManyToMany(BusinessDirection entity) {
        List<Long> companyIds = getCompanyIdsByBusinessDirection(entity);
        for (Long companyId : companyIds) {
            if (!entity.getCompanies().stream().anyMatch(company -> company.getId().equals(companyId))) {
                try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryCompanyBusinessDirectionDeleteManyToMany(companyId, entity.getId()))) {
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("problem: = " + e.getMessage());
                }
            }
        }
        for (Company company : entity.getCompanies()) {
            long count = 0;
            try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCompanyBusinessDirectionCountManyToManyByIds(company.getId(), entity.getId()))) {
                resultSet.next();
                count = resultSet.getLong("count");
                if (count == 0) {
                    try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryManyToMany("company_business_direction"))) {
                        preparedStatement.setLong(1, company.getId());
                        preparedStatement.setLong(2, entity.getId());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("problem: = " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryCompanyBusinessDirectionDeleteByBusinessDirectionId(id))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.getQueryDeleteById("business_directions") + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCountById("business_directions") + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("count");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public BusinessDirection findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryFindById("business_directions") + id)) {
            while (resultSet.next()) {
                BusinessDirection businessDirection = initBusinessDirectionsByResultSet(resultSet);
                businessDirection.setCompanies(getCompaniesByBusinessDirection(businessDirection));
                return businessDirection;
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return new BusinessDirection();
    }

    @Override
    public ResponseDataTable<BusinessDirection> findAll(RequestDataTable request) {
        List<BusinessDirection> businessDirections = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryFindAll("business_directions", request))) {
            while (resultSet.next()) {
                BusinessDirection businessDirection = initBusinessDirectionsByResultSet(resultSet);
                businessDirections.add(businessDirection);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        for (BusinessDirection businessDirection : businessDirections) {
            businessDirection.setCompanies(getCompaniesByBusinessDirection(businessDirection));
        }
        ResponseDataTable<BusinessDirection> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(businessDirections);
        return responseDataTable;
    }

    @Override
    public ResponseDataTable<BusinessDirection> findAllByCompany(RequestDataTable request, long companyId) {
        List<BusinessDirection> businessDirections = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryBusinessDirectionsFindAllByCompany(request, companyId))) {
            while (resultSet.next()) {
                BusinessDirection businessDirection = initBusinessDirectionsByResultSet(resultSet);
                businessDirections.add(businessDirection);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        for (BusinessDirection businessDirection : businessDirections) {
            businessDirection.setCompanies(getCompaniesByBusinessDirection(businessDirection));
        }
        ResponseDataTable<BusinessDirection> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(businessDirections);
        return responseDataTable;
    }

    @Override
    public long countByCompany(long companyId) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryBusinessDirectionsCountByCompany(companyId))) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<BusinessDirection> findAll() {
        List<BusinessDirection> businessDirections = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryFindAll("business_directions"))) {
            while (resultSet.next()) {
                businessDirections.add(initBusinessDirectionsByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }

        for (BusinessDirection businessDirection : businessDirections) {
            businessDirection.setCompanies(getCompaniesByBusinessDirection(businessDirection));
        }
        return businessDirections;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCount("business_directions"))) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Long> getCompanyIdsByBusinessDirection(BusinessDirection entity) {
        List<Long> companyIds = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCompanyBusinessDirectionsByBusinessDirectionId(entity.getId()))) {
            while (resultSet.next()) {
                companyIds.add(resultSet.getLong("company_id"));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return companyIds;
    }

    private BusinessDirection initBusinessDirectionsByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean deletionMark = resultSet.getBoolean("deletion_mark");

        BusinessDirection businessDirection = new BusinessDirection();
        businessDirection.setId(id);
        businessDirection.setName(name);
        businessDirection.setCreated(created);
        businessDirection.setUpdated(updated);
        businessDirection.setDeletionMark(deletionMark);

        return businessDirection;
    }

    private Set<Company> getCompaniesByBusinessDirection(BusinessDirection businessDirection) {
        Set<Company> companies = new HashSet<>();
        List<Long> companyIds = getCompanyIdsByBusinessDirection(businessDirection);
        for (Long companyId : companyIds) {
            companies.add(companyDao.findById(companyId));
        }
        return companies;
    }
}
