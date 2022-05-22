package org.a3.queries; /**
 * @author (Dane Cowburn)
 * @version (1.0)
 * @date (2022-05-20)
 * @course (SENG2050)
 * @assignment (3.2)
 * @group (Dimitar Velovski, Stephen Watson, Dane Cowburn, Lindsey Neilson)
 * @studentNumber (c3232962)
 * @description: database query class for existing issue reports.
 */

import java.util.*;
import java.sql.*;
import javax.sql.*;

import org.a3.beans.ConfigBean;
import org.a3.beans.IssueReportBean;

public class IssueReportsQuery
{
    //Method for grabbing data of all stored reports from database
    public static List<IssueReportBean> getAllIssueReports ()
    {
        String query = "SELECT * FROM IssueReports";
        List<IssueReportBean> reports = new LinkedList<>();
        try (Connection connection = ConfigBean.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query);)
        {
            while (result.next()) //Iterate over and collect all data from each tuple in database
            {
                IssueReportBean report = new IssueReportBean();
                report.setCreatedBy(result.getString(1));
                report.setTitle(result.getString(2));
                report.setIssueDescription(result.getString(3));
                report.setIssueStatus(result.getBoolean(4));
                report.setReportedAt(result.getString(5));
                report.setResolvedAt(result.getString(6));
                report.setLocked(result.getBoolean(7));
                report.setAcceptedSolution(result.getBoolean(8));
                report.setCategory(result.getString(9));
                reports.add(report);
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
        return reports;
    }
}