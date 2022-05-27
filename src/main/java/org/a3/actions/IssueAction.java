package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UserBean;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

public class IssueAction extends BaseSessionAwareAction{
    private IssueReportBean issueReport;

    @Override
    public String doExecute() {
        //if 'action' is blank, we are viewing.
            //if user is staff, or creator, we can view

        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean user = sm.getUserBean(userSessionObject);
            //we kinda need to query the Issue before deciding if the user can see it. yucky

        }else{
            return ResponseCodes.FORBIDDEN;
        }

        return ResponseCodes.UNAUTHORIZED;
    }

    public IssueReportBean getIssueReport() {
        return issueReport;
    }

    public void setIssueReport(IssueReportBean issueReport) {
        this.issueReport = issueReport;
    }
}
