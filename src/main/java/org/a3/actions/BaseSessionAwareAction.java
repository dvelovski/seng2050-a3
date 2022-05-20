package org.a3.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.a3.services.SessionManager;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public abstract class BaseSessionAwareAction extends ActionSupport implements SessionAware {
    protected Map<String, Object> userSessionObject;
    @Override
    public void setSession(Map<String, Object> map) {
        this.userSessionObject = map;
    }

    @Override
    public String execute() {
        SessionManager.get().ensureSession(this.userSessionObject);
        return doExecute();
    }
    public abstract String doExecute();
}
