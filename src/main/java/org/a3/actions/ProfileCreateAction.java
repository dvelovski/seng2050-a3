package org.a3.actions;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

public class ProfileCreateAction extends BaseSessionAwareAction{
    private String uFirstName = "";
    private String uLastName = "";
    private String uEmail = "";
    private String uPhone = "";

    private String action = "";

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean currentUser = sm.getUserBean(userSessionObject);
            if (currentUser.getUserType() == UserType.Staff){
                if (action.isEmpty()){
                    return SUCCESS;
                }else{
                    System.out.println(action);
                    return ResponseCodes.CREATED;
                }
            }else{
                return ResponseCodes.FORBIDDEN;
            }
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public String getuPhone() {
        return uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public String getuLastName() {
        return uLastName;
    }

    public String getuFirstName() {
        return uFirstName;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
