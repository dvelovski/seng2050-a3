package org.a3.actions;

public class IssueCommentAction extends BaseSessionAwareAction{
    private int issueID;
    private int newCommentID; //the comment ID that was created as a result of this action successfully executing
    private String cCommentText;
    private boolean cResolvesIssue;

    @Override
    public String doExecute() {
        //TODO permission check
        //TODO uhh
        //TODO aaahghgh

        return null;
    }

    public String getcCommentText() {
        return cCommentText;
    }

    public void setcCommentText(String cCommentText) {
        this.cCommentText = cCommentText;
    }

    public boolean iscResolvesIssue() {
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
}
