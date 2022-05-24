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
            boolean proceed  = true;
            if (loginUsername.isEmpty()){
                System.out.println("adding field error - username");
                addFieldError("loginUsername", "Username may not be blank.");
                proceed = false;
            }
            if (loginPassword.isEmpty()) {
                System.out.println("adding field error - passw");
                addFieldError("loginPassword", "Password must be specified.");
                proceed = false;
            }
            if (proceed){
                if (loginUsername.equals("abc")){
                    sm.setLoggedIn(userSessionObject, true);
                    sm.setUserType(userSessionObject, UserType.User);
                    userType = UserType.User;
                    return SUCCESS;
                }else if (loginUsername.equals("asd")){
                    sm.setLoggedIn(userSessionObject, true);
                    sm.setUserType(userSessionObject, UserType.Staff);
                    userType = UserType.Staff;
                    return SUCCESS;
                }else{
                    addFieldError("loginGeneral", "The username or password was incorrect.");
                }
            }
        }else{
            return SUCCESS;
        }

        return ERROR;
    }
}
