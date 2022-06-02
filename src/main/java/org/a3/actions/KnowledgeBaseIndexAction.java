package org.a3.actions;

import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

public class KnowledgeBaseIndexAction extends BaseSessionAwareAction{
    private String searchCategory = "All";

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            return SUCCESS;
        }
        return ResponseCodes.UNAUTHORIZED;
    }
}
