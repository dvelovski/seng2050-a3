package org.a3.actions;

import org.a3.beans.*;
import org.a3.queries.CommentsQuery;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;

public class IssueAction extends BaseSessionAwareAction{
    private int id;
    private IssueReportBean issueReport;
    private List<UploadedFileBean> issueFiles;
    private List<CommentsBean> issueComments;
    private String statusClass = "issue_status_text ";

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

            if (issueReport != null){
                if (canViewIssue(issueReport, user)){
                    showAssignedUser = true;
                    allowCommentInput = true;
                    allowCommentMarkAsSolution = (user.getUserType() == UserType.Staff); //only staff can mark comments as solutions

                    int irStatus = issueReport.getIssueStatus();
                    int kbID = issueReport.getKnowledgeBaseArticleID();

                    switch (irStatus){
                        case 1:
                            statusClass += "new";
                            showAssignedUser = false;
                            break;
                        case 2:
                            statusClass += "inprogress";
                            break;
                        case 3:
                            statusClass += "resolved"; //db says 'completed'
                            showAcceptanceOptions = true;
                            allowCommentMarkAsSolution = false;
                            showKBPromotion = (user.getUserType() == UserType.Staff && kbID == 0);
                            break;
                        case 4:
                            statusClass += "completed"; //db says 'resolved'
                            allowCommentInput = false;
                            allowCommentMarkAsSolution = false;
                            showKBPromotion = (user.getUserType() == UserType.Staff && kbID == 0);
                            break;
                    }
                    //System.out.println("show promotion? " + showKBPromotion + " - " + user.getUserType() + " " + kbID);

                    //It is assumed that if you can view it, you can comment on it, unless it's Completed or Locked

                    if (issueReport.getLocked() || issueReport.getIssueStatus() == 4){ //completed
                        allowCommentInput = false;
                        allowCommentMarkAsSolution = false;
                    }

                    if (issueReport.getKnowledgeBaseArticleID() > 0){
                        showKBSegment = true;
                    }

                    //get files
                    issueFiles = viewQuery.getFilesForReport(id);

                    try (CommentsQuery cQuery = new CommentsQuery()){
                        issueComments = cQuery.getReportComments(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return SUCCESS;
                }else{
                    return ResponseCodes.FORBIDDEN;
                }
            }else{
                return ERROR;
            }
        }else{
            return ResponseCodes.UNAUTHORIZED;
        }
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

    public boolean getAllowCommentMarkAsSolution() {
        return allowCommentMarkAsSolution;
    }

    public boolean getAllowCommentInput() {
        return allowCommentInput;
    }

    public boolean getShowKBPromotion() {
        return showKBPromotion;
    }

    public boolean getShowKBSegment() {
        return showKBSegment;
    }

    public boolean getShowAcceptanceOptions() {
        return showAcceptanceOptions;
    }

    public boolean getShowAssignedUser() {
        return showAssignedUser;
    }

    public List<CommentsBean> getIssueComments() {
        return issueComments;
    }
}
