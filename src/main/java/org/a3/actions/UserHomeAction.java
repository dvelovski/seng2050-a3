package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.StatisticsCategoryBean;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
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

    private int pageNumber = 0;
    private int resultsPerPage = 10;
    private int resultsOnPage;
    private int resultStart;
    private int resultCount;

    private int nextPage;
    private int prevPage;

    private String action = "";

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
                    break;
                default:
                    homepageHeading = "My Issues";
                    showStatistics = false;
                    break;
            }

            int creatorID = (uBean.getUserType() == UserType.User ? uBean.getUserIdentification() : -1);
            int assignedID = (uBean.getUserType() == UserType.Staff ? uBean.getUserIdentification() : -1);

            try (IssueReportsQuery iQuery = new IssueReportsQuery()){
                //get the count first
                resultCount = iQuery.getIssueReportCount(creatorID, assignedID, false);
                int maxPages = Math.floorDiv(resultCount, resultsPerPage);

                switch (action){
                    case "p.first":
                        pageNumber = 0;
                        break;
                    case "p.prev":
                        pageNumber -= 1;
                        break;
                    case "p.next":
                        pageNumber += 1;
                        break;
                    case "p.last":
                        pageNumber = maxPages;
                        break;
                }

                pageNumber = Math.min(Math.max(0, pageNumber), maxPages);

                prevPage = Math.max(pageNumber - 1, 0);
                nextPage = Math.min(pageNumber + 1, maxPages);

                /*System.out.println("page number: " + pageNumber);
                System.out.println("maximum pages for " + resultCount + " is " + maxPages);
                System.out.println("prev page: " + prevPage);
                System.out.println("next page: " + nextPage);*/

                issueReports = iQuery.getIssueReports(
                        creatorID,
                        assignedID,
                        resultsPerPage * pageNumber,
                        resultsPerPage,
                        false,
                        false
                );
                resultsOnPage = Math.min(issueReports.size(), resultsPerPage) + (pageNumber * resultsPerPage);
                resultStart = (issueReports.isEmpty() ? 0 : (resultsPerPage * pageNumber) + 1);
            } catch (Exception e) {
                e.printStackTrace();
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

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
