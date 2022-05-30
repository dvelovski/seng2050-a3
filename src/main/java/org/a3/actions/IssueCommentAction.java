package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.ResourceBundle;

public class IssueCommentAction extends BaseSessionAwareAction{
    private int issueID;
    private int newCommentID; //the comment ID that was created as a result of this action successfully executing
    private String cCommentText;
    private boolean cResolvesIssue = false;

    private String creationErrorMessage = "";

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean user = sm.getUserBean(userSessionObject);
            IssueReportsQuery irQuery = new IssueReportsQuery();
            IssueReportBean reportBean = irQuery.getIssueReport(issueID);
            String innerStatus = SUCCESS;

            if (reportBean != null){
                boolean innerValidation = true;
                if (cResolvesIssue && user.getUserType() != UserType.Staff){
                    innerStatus = ERROR;
                    creationErrorMessage = "You don't have permission to do that.";
                    innerValidation = false;
                }else if (cCommentText.length() == 0){
                    innerStatus = ERROR;
                    creationErrorMessage = "You must add text to your comment.";
                    innerValidation = false;
                }else if (cCommentText.length() > 4096){
                    innerStatus = ERROR;
                    creationErrorMessage = "Your comment exceeds the maximum allowed length of 4096 characters.";
                    innerValidation = false;
                }
                if (innerValidation){
                    //TODO submit
                }
            }else{
                innerStatus = ERROR;
                creationErrorMessage = "The specified issue report could not be found.";
            }
            return innerStatus;
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public String getcCommentText() {
        return cCommentText;
    }

    public void setcCommentText(String cCommentText) {
        this.cCommentText = cCommentText;
    }

    public boolean getcResolvesIssue() {
        return cResolvesIssue;
    }

    public void setcResolvesIssue(boolean cResolvesIssue) {
        this.cResolvesIssue = cResolvesIssue;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public int getNewCommentID() {
        return newCommentID;
    }

    public void setNewCommentID(int newCommentID) {
        this.newCommentID = newCommentID;
    }

    public String getCreationErrorMessage() {
        return creationErrorMessage;
    }
}
