package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class UserHomeAction extends BaseSessionAwareAction{
    private String userType;
    private String homepageHeading;
    private boolean showStatistics;

    private List<IssueReportBean> issueReports;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            UserType uType = SessionManager.get().getUserType(userSessionObject);
            userType = uType.toString();

            switch (uType){
                case Staff:
                    homepageHeading = "My Assigned Issues";
                    showStatistics = true;
                    issueReports = IssueReportsQuery.getAllIssueReports();
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
}
