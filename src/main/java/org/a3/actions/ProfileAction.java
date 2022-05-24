package org.a3.actions;

import org.a3.beans.UserBean;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

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
        userBean = new UserBean();

        userBean.setUserEmail("test@abc.com");
        userBean.setUserFirstName("T");
        userBean.setUserLastName("S");
        userBean.setUserPhoneNumber("123456");

        if (sm.isLoggedIn(userSessionObject)) {
            if (action.isEmpty()){
                /* TODO if user is not staff, is this their own profile? If not, forbidden. */
                pageTitle = "User details - " + userBean.getUserFirstName() + " " + userBean.getUserLastName();
                return SUCCESS;
            }else if (action.equals("editor")){
                pageTitle = "Editing user details";
                return SUCCESS;
            }else if (action.equals("update")){

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
