package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.StatisticsCategoryBean;
import org.a3.beans.UserBean;
import org.a3.queries.IssueReportsQuery;
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
    private String stressRate;

    private int pageNumber = 1;
    private int resultsPerPage = 10;
    private int resultsOnPage;
    private int resultStart;

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
                        stressRate = String.format("%.2f", statQuery.getStressRate());

                    } catch (SQLException e) {
                        statisticsError = e.getMessage();
                    }
                    //issueReports = IssueReportsQuery.getAllIssueReports();
                    try (IssueReportsQuery iQuery = new IssueReportsQuery()){
                        issueReports = iQuery.getIssueReports(-1, uBean.getUserIdentification(), resultsPerPage * (pageNumber - 1), resultsPerPage);
                        resultsOnPage = Math.min(issueReports.size(), resultsPerPage);
                        resultStart = (resultsPerPage * (pageNumber - 1)) + 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    homepageHeading = "My Issues";
                    showStatistics = false;

                    try (IssueReportsQuery iQuery = new IssueReportsQuery()){
                        issueReports = iQuery.getIssueReports(uBean.getUserIdentification(), -1, resultsPerPage * (pageNumber - 1), resultsPerPage);
                        resultsOnPage = Math.min(issueReports.size(), resultsPerPage);
                        resultStart = (resultsPerPage * (pageNumber - 1)) + 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    public String getStressRate() {
        return stressRate;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public int getResultsOnPage() {
        return resultsOnPage;
    }

    public void setResultsOnPage(int resultsOnPage) {
        this.resultsOnPage = resultsOnPage;
    }

    public int getResultStart() {
        return resultStart;
    }

    public void setResultStart(int resultStart) {
        this.resultStart = resultStart;
    }
}
