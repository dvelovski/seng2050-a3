package org.a3.actions;

import org.a3.beans.KnowledgeBaseBean;
import org.a3.queries.KnowledgeBaseQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.Collections;
import java.util.List;

public class SearchAction extends BaseSessionAwareAction {
    private String searchQuery = "";
    private String filterCategory = "";
    private int fCatInt = 0;

    private List<KnowledgeBaseBean> results;
    public String doExecute() {
        return null;
    }

    public String populate(){
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            fCatInt = filterCategory.isEmpty() ? 0 : Integer.parseInt(filterCategory);

            if (fCatInt < 0 || fCatInt > 14){
                fCatInt = 0;
            }

            try (KnowledgeBaseQuery kbQuery = new KnowledgeBaseQuery()){
                if (searchQuery.isEmpty()){ //avoid errors, and avoid making the query code even more spaghetti-like
                    results = kbQuery.getKBArticleIndex(fCatInt);
                }else{
                    results = kbQuery.searchKB(fCatInt, searchQuery);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return SUCCESS;
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public String getSearchQuery(){
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<KnowledgeBaseBean> getResults(){
        return results;
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
}
