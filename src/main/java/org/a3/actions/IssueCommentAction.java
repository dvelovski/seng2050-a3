package org.a3.actions;

public class IssueCommentAction extends BaseSessionAwareAction{
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
}
