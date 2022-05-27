package org.a3.actions;

import com.opensymphony.xwork2.interceptor.Interceptor;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.UserQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileCreateAction extends BaseSessionAwareAction{
    private String uFirstName = "";
    private String uLastName = "";
    private String uEmail = "";
    private String uPhone = "";

    private String uAcctType = "";

    private String action = "";

    private String newUserName = "";
    private String newUserPass = "";

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean currentUser = sm.getUserBean(userSessionObject);
            if (currentUser.getUserType() == UserType.Staff){
                if (action.isEmpty()){
                    return SUCCESS;
                }else{
                    //TODO validation
                    try {
                        String[] details = new UserQuery().createUser(uFirstName, uLastName, uEmail, uPhone, Integer.parseInt(uAcctType));
                        newUserName = details[0];
                        newUserPass = details[1];
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return ERROR;
                    }

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getuAcctType() {
        return uAcctType;
    }

    public void setuAcctType(String uAcctType) {
        this.uAcctType = uAcctType;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public String getNewUserPass() {
        return newUserPass;
    }
}
