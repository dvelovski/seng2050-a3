package org.a3.actions;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

public class KnowledgeBasePublishAction extends BaseSessionAwareAction{
    private int articleResultID;
    private int issueID;
    private String kbArticleDesc;
    private String kbArticleName;
    private String kbArticleResolution;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            UserBean user = sm.getUserBean(userSessionObject);
            if (user.getUserType() == UserType.Staff){

                //TODO create the entry and return its ID so we can navigate

                return SUCCESS;
            }
            return ResponseCodes.FORBIDDEN;
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public int getArticleResultID() {
        return articleResultID;
    }

    public void setArticleResultID(int articleResultID) {
        this.articleResultID = articleResultID;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getKbArticleDesc() {
        return kbArticleDesc;
    }

    public void setKbArticleDesc(String kbArticleDesc) {
        this.kbArticleDesc = kbArticleDesc;
    }

    public String getKbArticleName() {
        return kbArticleName;
    }

    public void setKbArticleName(String kbArticleName) {
        this.kbArticleName = kbArticleName;
    }

    public String getKbArticleResolution() {
        return kbArticleResolution;
    }

    public void setKbArticleResolution(String kbArticleResolution) {
        this.kbArticleResolution = kbArticleResolution;
    }
}
