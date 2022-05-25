package org.a3.actions;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.UserQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.sql.SQLException;

public class ProfileAction extends BaseSessionAwareAction{
    private int userID;
    private UserBean userBean;
    private String action = "";

    private String uFirstName = "";
    private String uLastName = "";
    private String uEmail = "";
    private String uPhone = "";

    private String pageTitle;
    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();

        if (sm.isLoggedIn(userSessionObject)) {
            /* TODO if user is not staff, is this their own profile? If not, forbidden. */
            UserBean currentUser = sm.getUserBean(userSessionObject);
            UserQuery userQuery = new UserQuery();

            if (currentUser.getUserIdentification() == userID || currentUser.getStaffRoleOrPosition() == UserType.Staff){
                try {
                    userBean = userQuery.userDetailsQuery(userID);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return ERROR;
                }

                if (userBean != null){
                    if (action.isEmpty()){
                        pageTitle = "User details - " + userBean.getUserFirstName() + " " + userBean.getUserLastName();
                        return SUCCESS;
                    }else if (action.equals("editor")){
                        pageTitle = "Editing user details";
                        return SUCCESS;
                    }
                }else{
                    return ResponseCodes.NOTFOUND;
                }
            }else{
                return ResponseCodes.FORBIDDEN;
            }
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getuFirstName() {
        return uFirstName;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public String getuLastName() {
        return uLastName;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public String getPageTitle() {
        return pageTitle;
    }
}
