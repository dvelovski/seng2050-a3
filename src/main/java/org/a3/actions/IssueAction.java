package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UploadedFileBean;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;

public class IssueAction extends BaseSessionAwareAction{
    private int id;
    private IssueReportBean issueReport;
    private List<UploadedFileBean> issueFiles;
    private String statusClass = "issue_status_text ";

    //TODO showComments
    private boolean allowCommentInput;
    private boolean allowCommentMarkAsSolution;

    private boolean showKBSegment; //if promoted to knowledge base, link to it here
    private boolean showKBPromotion;

    private boolean showAcceptanceOptions;

    private boolean showAssignedUser;

    @Override
    public String doExecute() {
        //if 'action' is blank, we are viewing.
            //if user is staff, or creator, we can view

        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean user = sm.getUserBean(userSessionObject);
            //we kinda need to query the Issue before deciding if the user can see it. yucky
            IssueReportsQuery viewQuery = new IssueReportsQuery();
            issueReport = viewQuery.getIssueReport(id);
            if (issueReport != null && canViewIssue(issueReport, user)){
                switch (issueReport.getIssueStatus()){
                    case 1:
                        statusClass += "new";
                        break;
                    case 2:
                        statusClass += "inprogress";
                        break;
                    case 3:
                        statusClass += "resolved";
                        break;
                    case 4:
                        statusClass += "completed";
                        break;
                }
                switch (issueReport.getIssueStatus()){
                    case 1: /* new */
                        allowCommentInput = false;
                        break;
                }

                if (issueReport.getLocked()){
                    allowCommentInput = false;
                    allowCommentMarkAsSolution = false;
                }
                if (issueReport.getKnowledgeBaseArticleID() != 0){
                    showKBSegment = true;
                    showKBPromotion = false;
                }

                //get files
                issueFiles = viewQuery.getFilesForReport(id);
                System.out.println(issueFiles);

                return SUCCESS;
            }else{
                return ERROR;
            }
        }else{
            return ResponseCodes.FORBIDDEN;
        }

        /*return ResponseCodes.UNAUTHORIZED;*/
    }

    private boolean canViewIssue(IssueReportBean i, UserBean u){
        return u.getUserType() == UserType.Staff || i.getCreatorID() == u.getUserIdentification();
    }

    public IssueReportBean getIssueReport() {
        return issueReport;
    }

    public void setIssueReport(IssueReportBean issueReport) {
        this.issueReport = issueReport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusClass() {
        return statusClass;
    }

    public List<UploadedFileBean> getIssueFiles() {
        return issueFiles;
    }
}
