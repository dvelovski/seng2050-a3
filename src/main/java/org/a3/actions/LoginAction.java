package org.a3.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.a3.UserType;
import org.a3.services.SessionManager;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LoginAction extends BaseSessionAwareAction {
    private String loginUsername = "";
    private String loginPassword = "";




    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String doExecute(){
        SessionManager sm = SessionManager.get();

        if (!sm.isLoggedIn(userSessionObject)){
            /* authentication flow */
            if (loginUsername.equals("abc")){
                sm.setLoggedIn(userSessionObject, true);
                sm.setUserType(userSessionObject, UserType.User);
                return SUCCESS;
            }
            if (loginUsername.equals("asd")){
                sm.setLoggedIn(userSessionObject, true);
                sm.setUserType(userSessionObject, UserType.User);
                return SUCCESS;
            }
        }else{
            return SUCCESS;
        }

        return ERROR;
    }
}
