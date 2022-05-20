package org.a3.actions;

import org.a3.services.SessionManager;

public class LogoutAction extends BaseSessionAwareAction{
    @Override
    public String doExecute() {
        SessionManager.get().destroySession(this.userSessionObject);
        return SUCCESS;
    }
}
