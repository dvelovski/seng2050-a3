package org.a3.actions;

import com.opensymphony.xwork2.interceptor.Interceptor;
import org.a3.beans.KnowledgeBaseBean;
import org.a3.queries.KnowledgeBaseQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;

public class KnowledgeBaseIndexAction extends BaseSessionAwareAction{
    private List<KnowledgeBaseBean> results;
    private String searchQuery = "";
    private String filterCategory = "";
    private int fCatInt = 0;
    private String fCatName = "";
    @Override
    public String doExecute() {
        if (searchQuery.isEmpty()){
            SessionManager sm = SessionManager.get();
            if (sm.isLoggedIn(userSessionObject)){
                fCatInt = filterCategory.isEmpty() ? 0 : Integer.parseInt(filterCategory);
                if (fCatInt < 0 || fCatInt > 14){
                    fCatInt = 0;
                }

                try (KnowledgeBaseQuery kbQuery = new KnowledgeBaseQuery()){
                    results = kbQuery.getKBArticleIndex(fCatInt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fCatInt == 0){
                    fCatName = "All";
                }else{
                    fCatName = (results.isEmpty() ? "" : results.get(0).getSubCategoryName());
                }

                return SUCCESS;
            }
            return ResponseCodes.UNAUTHORIZED;
        }else{
            return "search";
        }
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    public int getfCatInt() {
        return fCatInt;
    }

    public void setfCatInt(int fCatInt) {
        this.fCatInt = fCatInt;
    }

    public List<KnowledgeBaseBean> getResults() {
        return results;
    }

    public void setResults(List<KnowledgeBaseBean> results) {
        this.results = results;
    }

    public String getfCatName() {
        return fCatName;
    }

    public void setfCatName(String fCatName) {
        this.fCatName = fCatName;
    }
}
