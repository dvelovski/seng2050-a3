package org.a3.actions;

import org.a3.beans.KnowledgeBase;

public class KnowledgeBaseViewAction extends BaseSessionAwareAction{
    private KnowledgeBase articleBean;
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

    public KnowledgeBase getArticleBean() {
        return articleBean;
    }

    public void setArticleBean(KnowledgeBase articleBean) {
        this.articleBean = articleBean;
    }
}
