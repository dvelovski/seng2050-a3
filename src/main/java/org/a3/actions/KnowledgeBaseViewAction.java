package org.a3.actions;

import org.a3.beans.KnowledgeBaseBean;

public class KnowledgeBaseViewAction extends BaseSessionAwareAction{
    private KnowledgeBaseBean articleBean;
    private int articleID;

    @Override
    public String doExecute() {
        return SUCCESS;
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
}
