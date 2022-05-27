package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.StatisticsCategoryBean;
import org.a3.beans.UserBean;
import org.a3.queries.StatisticsQuery;
import org.a3.services.SessionManager;

import java.sql.SQLException;
import java.util.List;

public class UserHomeAction extends BaseSessionAwareAction{
    private String userType;
    private String homepageHeading;
    private boolean showStatistics;
    private String statisticsError; //error message to show if we can't load statistics

    private List<IssueReportBean> issueReports;
    private List<StatisticsCategoryBean> issueCategories;
    private double stressRate;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            UserBean uBean = SessionManager.get().getUserBean(userSessionObject);
            userType = uBean.getUserType().toString();

            switch (uBean.getUserType()){
                case Staff:
                    homepageHeading = "My Assigned Issues";
                    showStatistics = true;
                    try {
                        StatisticsQuery statQuery = new StatisticsQuery();
                        issueCategories = statQuery.getCategorizedStatistics();
                        stressRate = statQuery.getStressRate();

                    } catch (SQLException e) {
                        statisticsError = e.getMessage();
                    }
                    //issueReports = IssueReportsQuery.getAllIssueReports();
                    break;
                default:
                    homepageHeading = "My Issues";
                    showStatistics = false;
                    break;
            }
            return SUCCESS;
        }else{
            return ERROR;
        }
    }

    public String getUserType(){
        return userType;
    }
    public String getHomepageHeading(){
        return homepageHeading;
    }

    public boolean getShowStatistics() {
        return showStatistics;
    }

    public List<IssueReportBean> getIssueReports() {
        return issueReports;
    }

    public List<StatisticsCategoryBean> getIssueCategories() {
        return issueCategories;
    }

    public double getStressRate() {
        return stressRate;
    }
}
