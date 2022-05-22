package org.a3.queries; /**
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
import javax.sql.*;

import org.a3.beans.ConfigBean;
import org.a3.beans.StatisticsBean;

public class StatisticsQuery
{
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