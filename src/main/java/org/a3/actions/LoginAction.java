package org.a3.actions;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.UserQuery;
import org.a3.services.SessionManager;

import java.sql.SQLException;

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
                //System.out.println("adding field error - username");
                addFieldError("loginUsername", "Username may not be blank.");
                proceed = false;
            }
            if (loginPassword.isEmpty()) {
                //System.out.println("adding field error - passw");
                addFieldError("loginPassword", "Password must be specified.");
                proceed = false;
            }

            if (proceed){
                try {
                    UserBean result = new UserQuery().loginQuery(loginUsername, loginPassword);
                    if (result != null){
                        sm.setLoggedIn(userSessionObject, true);
                        sm.setUserBean(userSessionObject, result);

                        return SUCCESS;
                    }else{
                        addFieldError("loginGeneral", "The username or password was incorrect.");
                    }
                } catch (SQLException e) {
                    addFieldError("loginGeneral", e.getMessage());
                    e.printStackTrace();
                }
            }
        }else{
            return SUCCESS;
        }

        return ERROR;
    }
}
