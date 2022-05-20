package org.a3.actions;

import java.util.Collections;
import java.util.List;

public class SearchAction extends BaseSessionAwareAction {
    private String searchQuery;

    private List<Object> results;
    public String doExecute() {
        return null;
    }

    public String populate(){
        results = Collections.emptyList();
        /*results = List.of("a", "b", "c");*/
        return SUCCESS;
    }

    public String getSearchQuery(){
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Object> getResults(){
        return results;
    }
}
