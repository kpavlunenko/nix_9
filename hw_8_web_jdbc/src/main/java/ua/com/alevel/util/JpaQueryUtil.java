package ua.com.alevel.util;

import ua.com.alevel.persistence.datatable.RequestDataTable;

import java.util.List;

public final class JpaQueryUtil {

    private JpaQueryUtil() {
    }

    public static String getQueryUpdateById(String tableName, List<String> columns) {
        String query = "update " + tableName + " set";
        for (int i = 0; i < columns.size(); i++) {
            query = query + " " + columns.get(i) + " = ?";
            if (i != columns.size() - 1) {
                query = query + ",";
            }
        }
        query = query + " where id = ";
        return query;
    }

    public static String getQueryCreate(String tableName, int countOfColumns) {
        String query = "insert into " + tableName + " values(default";
        for (int i = 0; i < countOfColumns; i++) {
            query = query + ",?";
        }
        query = query + ")";
        return query;
    }

    public static String getQueryManyToMany(String tableName) {
        String query = "insert into " + tableName + " values(?, ?)";
        return query;
    }

    public static String getQueryCompanyBusinessDirectionDeleteManyToMany(long companyId, long businessDirectionId) {
        String query = "delete from company_business_direction where company_id = " + companyId + " and business_direction_id = " + businessDirectionId;
        return query;
    }

    public static String getQueryCompanyBusinessDirectionDeleteByBusinessDirectionId(long businessDirectionId) {
        String query = "delete from company_business_direction where business_direction_id = " + businessDirectionId;
        return query;
    }

    public static String getQueryCompanyBusinessDirectionDeleteByCompanyId(long companyId) {
        String query = "delete from company_business_direction where company_id = " + companyId;
        return query;
    }

    public static String getQueryCompanyBusinessDirectionCountManyToManyByIds(long companyId, long businessDirectionId) {
        return "select count(*) as count from company_business_direction where company_id = " + companyId + " and business_direction_id = " + businessDirectionId;
    }

    public static String getQueryCompanyBusinessDirectionsByBusinessDirectionId(long businessDirectionId) {
        return "select * from company_business_direction where business_direction_id = " + businessDirectionId;
    }

    public static String getQueryCompanyBusinessDirectionsByCompanyId(long companyId) {
        return "select * from company_business_direction where company_id = " + companyId;
    }

    public static String getQueryDeleteById(String tableName) {
        return "delete from " + tableName + " where id = ";
    }

    public static String getQueryFindById(String tableName) {
        return "select * from " + tableName + " where id = ";
    }

    public static String getQueryCountById(String tableName) {
        return "select count(*) as count from " + tableName + " where id = ";
    }

    public static String getQueryCount(String tableName) {
        return "select count(*) as count from " + tableName;
    }

    public static String getQueryFindAll(String tableName, RequestDataTable request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sqlQuery = "select * from " + tableName + " order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        return sqlQuery;
    }

    public static String getQueryCounterpartiesCountByCompany(long companyId) {
        String sqlQuery = "select count(counterparties.id) as count " +
                "from counterparties join agreements agreements on counterparties.id = agreements.counterparty_id where company_id = " + companyId;
        return sqlQuery;
    }

    public static String getQueryBusinessDirectionsCountByCompany(long companyId) {
        String sqlQuery = "select count(company_id) as count " +
                "from company_business_direction where company_id = " + companyId;
        return sqlQuery;
    }

    public static String getQueryCompanyFindAll(RequestDataTable request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sqlQuery = "\n" +
                "select companies.id as id,\n" +
                "       companies.created as created,\n" +
                "       companies.updated as updated,\n" +
                "       companies.deletion_mark as deletion_mark,\n" +
                "       companies.name as name,\n" +
                "       companies.company_type as company_type,\n" +
                "       count(agreements.counterparty_id) as counterparties\n" +
                "from companies\n" +
                "        left join agreements as agreements on companies.id = agreements.company_id group by companies.id, companies.created, companies.updated, companies.deletion_mark, companies.name, companies.company_type\n" +
                "order by " + request.getSort() + " " + request.getOrder() + "\n" +
                "limit " + limit + "," + request.getPageSize() + ";";
        return sqlQuery;
    }

    public static String getQueryCounterpartyFindAll(RequestDataTable request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sqlQuery = "\n" +
                "select counterparties.id as id,\n" +
                "       counterparties.created as created,\n" +
                "       counterparties.updated as updated,\n" +
                "       counterparties.deletion_mark as deletion_mark,\n" +
                "       counterparties.name as name,\n" +
                "       counterparties.counterparty_type as counterparty_type,\n" +
                "       counterparties.inn as inn,\n" +
                "       count(agreements.company_id) as agreements\n" +
                "from counterparties\n" +
                "        left join agreements as agreements on counterparties.id = agreements.counterparty_id group by counterparties.id, counterparties.created, counterparties.updated, counterparties.deletion_mark, counterparties.name, counterparties.inn, counterparties.counterparty_type\n" +
                "order by " + request.getSort() + " " + request.getOrder() + "\n" +
                "limit " + limit + "," + request.getPageSize() + ";";
        return sqlQuery;
    }

    public static String getQueryCounterpartiesFindAllByCompany(RequestDataTable request, long companyId) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sqlQuery = "select counterparties.id,\n" +
                "       counterparties.created,\n" +
                "       counterparties.updated,\n" +
                "       counterparties.deletion_mark,\n" +
                "       counterparties.name,\n" +
                "       counterparties.counterparty_type,\n" +
                "       counterparties.inn,\n" +
                "       agreements.company_id,\n" +
                "       count(agreementsByCounterparty.company_id) as agreements\n" +
                "from counterparties\n" +
                "         join agreements agreements on counterparties.id = agreements.counterparty_id\n" +
                "         left join agreements as agreementsByCounterparty on counterparties.id = agreementsByCounterparty.counterparty_id\n" +
                "where agreements.company_id =" + companyId + "\n" +
                "group by counterparties.id,\n" +
                "         counterparties.created,\n" +
                "         counterparties.updated,\n" +
                "         counterparties.deletion_mark,\n" +
                "         counterparties.name,\n" +
                "         counterparties.counterparty_type,\n" +
                "         counterparties.inn,\n" +
                "         agreements.company_id\n" +
                "order by " + request.getSort() + " " + request.getOrder() + "\n" +
                "limit " + limit + "," + request.getPageSize() + ";";
        return sqlQuery;
    }

    public static String getQueryBusinessDirectionsFindAllByCompany(RequestDataTable request, long companyId) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sqlQuery = "select *\n" +
                "from business_directions\n" +
                "         join company_business_direction cbd on business_directions.id = cbd.business_direction_id\n" +
                "where company_id = 14\n" +
                "order by " + request.getSort() + " " + request.getOrder() + "\n" +
                "limit " + limit + "," + request.getPageSize() + ";";
        return sqlQuery;
    }

    public static String getQueryFindAll(String tableName) {
        String sqlQuery = "select * from " + tableName + " order by name";
        return sqlQuery;
    }

    public static String getQueryAgreementFindAll(RequestDataTable request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String query = "select * from agreements as agreement " +
                "join companies as company on agreement.company_id = company.id " +
                "join counterparties as counterparty on agreement.counterparty_id = counterparty.id"
                + " order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        return query;
    }

    public static String getQueryAgreementFindAllByCounterparty(RequestDataTable request, long counterpartyId) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String query = "select * from agreements as agreement " +
                "join companies as company on agreement.company_id = company.id " +
                "join counterparties as counterparty on agreement.counterparty_id = counterparty.id where agreement.counterparty_id = " + counterpartyId + ""
                + " order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        return query;
    }

    public static String getQueryAgreementsCountByCounterparty(long counterpartyId) {
        String sqlQuery = "select count(agreements.id) as count " +
                "from agreements where counterparty_id = " + counterpartyId;
        return sqlQuery;
    }

    public static String getQueryAgreementsCountByCompany(long companyId) {
        String sqlQuery = "select count(agreements.id) as count " +
                "from agreements where company_id = " + companyId;
        return sqlQuery;
    }

    public static String getQueryAgreementFindAll() {
        String query = "select * from agreements as agreement " +
                "join companies as company on agreement.company_id = company.id " +
                "join counterparties as counterparty on agreement.counterparty_id = counterparty.id";
        return query;
    }

    public static String getQueryAgreementFindById() {
        String query = "select * from agreements as agreement " +
                "join companies as company on agreement.company_id = company.id " +
                "join counterparties as counterparty on agreement.counterparty_id = counterparty.id " +
                "where agreement.id = ";
        return query;
    }
}
