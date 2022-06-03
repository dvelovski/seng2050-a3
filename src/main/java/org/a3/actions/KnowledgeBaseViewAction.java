package org.a3.actions;

import org.a3.beans.KnowledgeBaseBean;
import org.a3.beans.UploadedFileBean;
import org.a3.queries.IssueReportsQuery;
import org.a3.queries.KnowledgeBaseQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;

public class KnowledgeBaseViewAction extends BaseSessionAwareAction{
    private KnowledgeBaseBean articleBean;
    private List<UploadedFileBean> issueFiles;
    private int articleID;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            String innerStatus = SUCCESS;
            try (KnowledgeBaseQuery kbQuery = new KnowledgeBaseQuery()){
                articleBean = kbQuery.getKBArticle(articleID);

                if (articleBean == null) {
                    innerStatus = ERROR;
                }else{
                    try (IssueReportsQuery irQuery = new IssueReportsQuery()){
                        issueFiles = irQuery.getFilesForReport(articleBean.getInitialissue());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return innerStatus;
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public KnowledgeBaseBean getArticleBean() {
        return articleBean;
    }

    public void setArticleBean(KnowledgeBaseBean articleBean) {
        this.articleBean = articleBean;
    }

    public List<UploadedFileBean> getIssueFiles() {
        return issueFiles;
    }

    public void setIssueFiles(List<UploadedFileBean> issueFiles) {
        this.issueFiles = issueFiles;
    }
}
