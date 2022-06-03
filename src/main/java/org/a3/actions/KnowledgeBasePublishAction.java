package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.queries.KnowledgeBaseQuery;
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
                IssueReportBean originalReport = null;
                String innerStatus = SUCCESS;

                try (IssueReportsQuery irQuery = new IssueReportsQuery()) {
                    originalReport = irQuery.getIssueReport(issueID);

                } catch (Exception e) {
                    e.printStackTrace();
                    innerStatus = ERROR;
                }

                if (originalReport != null){
                    try (KnowledgeBaseQuery kbQuery = new KnowledgeBaseQuery()){
                        articleResultID = kbQuery.createKBArticle(issueID, kbArticleName, kbArticleDesc, kbArticleResolution);
                        innerStatus = SUCCESS;
                    } catch (Exception e) {
                        e.printStackTrace();
                        innerStatus = ERROR;
                    }
                }

                return innerStatus;
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
