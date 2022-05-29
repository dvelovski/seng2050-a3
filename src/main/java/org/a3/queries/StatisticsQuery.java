package org.a3.queries;
/*
 * @author (Dane Cowburn)
 * @version (1.0)
 * @date (2022-05-20)
 * @course (SENG2050)
 * @assignment (3.2)
 * @group (Dimitar Velovski, Stephen Watson, Dane Cowburn, Lindsey Neilson)
 * @studentNumber (c3232962)
 * @description: database query class for calculating statisitics from issue reports.
 */

import java.util.*;
import java.sql.*;

import org.a3.services.JDBCUtil;
import org.a3.beans.StatisticsCategoryBean;

public class StatisticsQuery
{
    /** author dvelovski **/
    public List<StatisticsCategoryBean> getCategorizedStatistics() throws SQLException {
        try (Connection conn = JDBCUtil.get().createConnection()){
            HashMap<String, StatisticsCategoryBean> results = new LinkedHashMap<>(); //preserve order

            String statsQuery = "SELECT\n" +
                    "    SubCategory.id,\n" +
                    "    SubCategory.categoryName AS \"Subcategory Name\",\n" +
                    "    (SELECT Category.categoryName WHERE Category.id = SubCategory.subCategoryOf) AS \"Subcategory Of\",\n" +
                    "    Category.id,\n" +
                    "    COUNT(cat) AS \"Total Unresolved\",\n" +
                    "    COUNT((IIF(val >= 10, 1, NULL))) AS \"Resolved In Last 7 Days\"\n" +
                    "FROM\n" +
                    "    Test1\n" +
                    "RIGHT JOIN SubCategory ON Test1.cat = SubCategory.id\n" +
                    "RIGHT JOIN Category ON SubCategory.subCategoryOf = Category.id\n" +
                    "GROUP BY SubCategory.id, SubCategory.subCategoryOf, Category.id, SubCategory.categoryName, Category.categoryName;";

            //TODO change the conditions for the 'resolved in last 7 days' query
            //TODO need a test / condition for 'total unresolved'. i.e. IIF IssueReports.acceptedSolution == 0, 1, NULL
            //COUNT ignores null values

            PreparedStatement statsStatement = conn.prepareStatement(statsQuery);
            ResultSet statsResults = statsStatement.executeQuery();

            while (statsResults.next()){
                int subCategoryID = statsResults.getInt(1);
                String subCategoryName = statsResults.getString(2);
                String subCategoryOf = statsResults.getString(3);
                int categoryID = statsResults.getInt(4);
                int totalUnresolved = statsResults.getInt(5);
                int resolvedLastSevenDays = statsResults.getInt(6);

                StatisticsCategoryBean parentCategory = results.computeIfAbsent(subCategoryOf, s -> new StatisticsCategoryBean());
                //System.out.println(subCategoryOf + parentCategory);
                if (parentCategory.getSubCategoryBeans() == null){
                    parentCategory.setSubCategoryBeans(new ArrayList<>());
                    parentCategory.setCategoryName(subCategoryOf);
                }

                StatisticsCategoryBean childCategory = new StatisticsCategoryBean();
                childCategory.setCategoryName(subCategoryName);
                childCategory.setTotalUnresolved(totalUnresolved);
                childCategory.setResolvedLastSevenDays(resolvedLastSevenDays);

                parentCategory.getSubCategoryBeans().add(childCategory);
            }

            results.values().forEach(bean -> {
                int tUnresolved = bean.getSubCategoryBeans().stream().mapToInt(StatisticsCategoryBean::getTotalUnresolved).sum();
                int tSevenDays = bean.getSubCategoryBeans().stream().mapToInt(StatisticsCategoryBean::getResolvedLastSevenDays).sum();

                bean.setTotalUnresolved(tUnresolved);
                bean.setResolvedLastSevenDays(tSevenDays);
            });

            return new ArrayList<>(results.values());
        }
    }

    public double getStressRate() throws SQLException{
        try (Connection conn = JDBCUtil.get().createConnection()){
            String stressQuery = "SELECT (SELECT COUNT(*) FROM IssueReports WHERE acceptedSolution = 0) AS \"Unresolved Issues\", \n" +
                    "(SELECT COUNT(*) FROM Users WHERE userRole = 2) AS \"Staff Count\"";

            PreparedStatement stressStatements = conn.prepareStatement(stressQuery);
            stressStatements.executeQuery();

            ResultSet stressResults = stressStatements.getResultSet();
            double tUnresolved = stressResults.getInt(1);
            double tStaff = stressResults.getInt(2);

            return tUnresolved / (tStaff * 5);
        }
    }

    //Method for grabbing data on Total Unsolved Incidents across all categories from
    // the IssueReports table in database
    /*public static List<StatisticsBean> getTUIStatistics ()
    {
        String networkTUIquery = "SELECT COUNT(*)\n" +
                                "FROM IssueReports\n" +
                                "WHERE (category BETWEEN 1 AND 3) AND (acceptedSolution = 0);";
        String hardwareTUIquery = "SELECT COUNT(*)\n" +
                                "FROM IssueReports\n" +
                                "WHERE (category BETWEEN 4 AND 5) AND (acceptedSolution = 0);";
        String softwareTUIquery = "SELECT COUNT(*)\n" +
                                "FROM IssueReports\n" +
                                "WHERE (category BETWEEN 6 AND 9) AND (acceptedSolution = 0);";
        String emailTUIquery = "SELECT COUNT(*)\n" +
                                "FROM IssueReports\n" +
                                "WHERE (category BETWEEN 10 AND 12) AND (acceptedSolution = 0);";
        String accountTUIquery = "SELECT COUNT(*)\n" +
                                "FROM IssueReports\n" +
                                "WHERE (category BETWEEN 13 AND 14) AND (acceptedSolution = 0);";
        List<StatisticsBean> tuiStatistics = new LinkedList<>();
        try (Connection connection = ConfigBean.getConnection();
             Statement statement = connection.createStatement();
             ResultSet nTUIResult = statement.executeQuery(networkTUIquery);
             ResultSet hTUIResult = statement.executeQuery(hardwareTUIquery);
             ResultSet sTUIResult = statement.executeQuery(softwareTUIquery);
             ResultSet eTUIResult = statement.executeQuery(emailTUIquery);
             ResultSet aTUIResult = statement.executeQuery(accountTUIquery);)
        {
            while (result.next()) //Iterate over and collect all data from each tuple in database
            {
                StatisticsBean statistic = new StatisticsBean();
                statistic.setNetworkTUI(nTUIResult.getInt(1));
                statistic.setHardwareTUI(hTUIResult.getInt(1));
                statistic.setSoftwareTUI(sTUIResult.getInt(1));
                statistic.setEmailTUI(eTUIResult.getInt(1));
                statistic.setAccountTUI(aTUIResult.getInt(1));
                tuiStatistics.add(statistic);
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
        return tuiStatistics;
    }

    //Method for grabbing data on Total Resolved Incidents across all categories from
    // the Issue Reports table in the database
    public static List<StatisticsBean> getTRIStatistics ()
    {
        String networkTRIquery = "SELECT COUNT(*)\n" +
                "FROM IssueReports\n" +
                "WHERE (category BETWEEN 1 AND 3) AND (acceptedSolution = 1);";
        String hardwareTRIquery = "SELECT COUNT(*)\n" +
                "FROM IssueReports\n" +
                "WHERE (category BETWEEN 4 AND 5) AND (acceptedSolution = 1);";
        String softwareTRIquery = "SELECT COUNT(*)\n" +
                "FROM IssueReports\n" +
                "WHERE (category BETWEEN 6 AND 9) AND (acceptedSolution = 1);";
        String emailTRIquery = "SELECT COUNT(*)\n" +
                "FROM IssueReports\n" +
                "WHERE (category BETWEEN 10 AND 12) AND (acceptedSolution = 1);";
        String accountTRIquery = "SELECT COUNT(*)\n" +
                "FROM IssueReports\n" +
                "WHERE (category BETWEEN 13 AND 14) AND (acceptedSolution = 1);";
        List<StatisticsBean> triStatistics = new LinkedList<>();
        try (Connection connection = ConfigBean.getConnection();
             Statement statement = connection.createStatement();
             ResultSet nTRIResult = statement.executeQuery(networkTRIquery);
             ResultSet hTRIResult = statement.executeQuery(hardwareTRIquery);
             ResultSet sTRIResult = statement.executeQuery(softwareTRIquery);
             ResultSet eTRIResult = statement.executeQuery(emailTRIquery);
             ResultSet aTRIResult = statement.executeQuery(accountTRIquery);)
        {
            while (result.next()) //Iterate over and collect all data from each tuple in database
            {
                StatisticsBean statistic = new StatisticsBean();
                statistic.setNetworkTRI(nTRIResult.getInt(1));
                statistic.setHardwareTRI(hTRIResult.getInt(1));
                statistic.setSoftwareTRI(sTRIResult.getInt(1));
                statistic.setEmailTRI(eTRIResult.getInt(1));
                statistic.setAccountTRI(aTRIResult.getInt(1));
                triStatistics.add(statistic);
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
        return triStatistics;
    }

    //Method for calculating stress rate which is found from the following equation:
    // stress rate = TUI all categories / (total number of IT Staff * 5)
    public static double calcStressRate ()
    {
        String queryTotalTUI = "SELECT COUNT(*)\n" +
                                "FROM IssueReports\n" +
                                "WHERE acceptedSolution = 0);";
        String queryTotalITStaff = "SELECT COUNT(*)\n" +
                                "FROM Users\n" +
                                "WHERE userRole = 2;";
        StatisticsBean statistics = new StatisticsBean();
        try(Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            ResultSet totalTUIResult = statement.executeQuery(queryTotalTUI);
            ResultSet totalITStaffResult = statement.executeQuery(queryTotalITStaff);)
        {
            double stressRate = (totalTUIResult.getInt(1)) / ((totalITStaffResult.getInt(1)) * 5);
            statistics.setStressRate(stressRate);
        } catch (SQLException e)
        {
                System.err.println(e.getMessage());
                System.err.println(e.getStackTrace());
        }
        return statistics.getStressRate();
    }*/
}