package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

public class IssueStatusUpdateAction extends BaseSessionAwareAction{
    private String action;
    private int issueID;
    private int requestorID;
    private int commentID;

    private String actionError;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            UserBean user = sm.getUserBean(userSessionObject);

            /* actions that i can accept are
                * 'assign' - must be staff
                *  reject solution - must be poster
                *  accept solution - must be poster
                *  else 'unknown action error'
                *  not handling 'lock' here
            */
            String response;
            try (IssueReportsQuery irQuery = new IssueReportsQuery()) {
                IssueReportBean iBean = irQuery.getIssueReport(issueID);

                response = SUCCESS;

                if (iBean != null) {
                    switch (action) {
                        case "i.assign":
                            if (user.getUserType() == UserType.Staff) {
                                irQuery.setIssueAssignedStaffMember(issueID, requestorID);
                                irQuery.setIssueStatus(issueID, 2);
                            } else {
                                response = ERROR;
                                actionError = "Only staff members are allowed to perform this action.";
                            }
                            break;
                        case "s.reject":
                            if (user.getUserIdentification() == iBean.getCreatorID()) {
                                irQuery.setIssueStatus(issueID, 2);
                                irQuery.setIssueProposedSolution(issueID, -1);
                                if (iBean.getKnowledgeBaseArticleID() != 0){
                                    System.out.println("article needs to be deleted from the knowledge base");
                                }
                            } else {
                                response = ERROR;
                                actionError = "Only the creator of the issue may perform this action.";
                            }
                            break;
                        case "s.accept":
                            if (user.getUserIdentification() == iBean.getCreatorID()) {
                                irQuery.setIssueStatus(issueID, 4);
                                irQuery.setIssueAcceptedSolution(issueID, commentID); //resolves it and locks it
                            } else {
                                response = ERROR;
                                actionError = "Only the creator of the issue may perform this action.";
                            }
                            break;
                        default:
                            response = ERROR;
                            actionError = "Unknown action requested.";
                            break;
                    }
                } else {
                    response = ERROR;
                }
                System.out.println("error - " + actionError + " for action " + action);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                actionError = e.getMessage();
                return ERROR;
            }
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getRequestorID() {
        return requestorID;
    }

    public void setRequestorID(int requestorID) {
        this.requestorID = requestorID;
    }

    public String getActionError() {
        return actionError;
    }

    public void setActionError(String actionError) {
        this.actionError = actionError;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}
