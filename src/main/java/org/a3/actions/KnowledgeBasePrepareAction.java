package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UploadedFileBean;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;

public class KnowledgeBasePrepareAction extends BaseSessionAwareAction{
    private int reportID;
    private int kbResultID;
    private String action = "";
    private String statusClass = "issue_status_text ";

    private IssueReportBean issueReport;
    private List<UploadedFileBean> issueFiles;

    private String creationErrorMessage = ""; //
    private boolean displayForm = true;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean user = sm.getUserBean(userSessionObject);
            if (user.getUserType() == UserType.Staff){
                IssueReportsQuery irQuery = new IssueReportsQuery();
                issueReport = irQuery.getIssueReport(reportID);

                String innerStatus = SUCCESS;

                if (issueReport != null){
                    if (issueReport.getIssueStatus() >= 3){
                        issueFiles = irQuery.getFilesForReport(reportID);
                        if (issueReport.getIssueStatus() == 3){
                            statusClass += "resolved"; //db says 'completed'
                        }else if (issueReport.getIssueStatus() == 4){
                            statusClass += "completed"; //db says 'resolved'
                        }
                    }else{
                        innerStatus = ERROR;
                        creationErrorMessage = "Issue is not in a valid state to be sent to the Knowledge Base.";
                        displayForm = false;
                    }
                    return innerStatus;
                }else{
                    innerStatus = ERROR;
                    creationErrorMessage = "The specified issue report could not be found.";
                    displayForm = false;
                }
                return innerStatus;
            }else{
                return ResponseCodes.FORBIDDEN;
            }
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getReportID() {
        return reportID;
    }

    public int getKbResultID() {
        return kbResultID;
    }

    public void setKbResultID(int kbResultID) {
        this.kbResultID = kbResultID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreationErrorMessage() {
        return creationErrorMessage;
    }

    public boolean getDisplayForm() {
        return displayForm;
    }

    public IssueReportBean getIssueReport() {
        return issueReport;
    }

    public List<UploadedFileBean> getIssueFiles() {
        return issueFiles;
    }

    public String getStatusClass() {
        return statusClass;
    }
}
