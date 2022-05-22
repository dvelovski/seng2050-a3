package org.a3.actions;

import org.a3.beans.UserType;
import org.a3.services.SessionManager;

public class LoginAction extends BaseSessionAwareAction {
    private String loginUsername = "";
    private String loginPassword = "";

    private UserType userType;


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
                userType = UserType.User;
                return SUCCESS;
            }
            if (loginUsername.equals("asd")){
                sm.setLoggedIn(userSessionObject, true);
                sm.setUserType(userSessionObject, UserType.Staff);
                userType = UserType.Staff;
                return SUCCESS;
            }
        }else{
            return SUCCESS;
        }

        return ERROR;
    }
}
