package org.a3.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.a3.services.SessionManager;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class IndexAction extends ActionSupport implements SessionAware{
    private Map<String, Object> userSession;

    public String execute(){
        SessionManager.get().ensureSession(userSession);
        if (userSession.get("loggedIn").equals(0)){
            return "no_login";
        }
        return "logged_in";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.userSession = map;
    }
}
