package org.a3.actions;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.UserQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.sql.SQLException;

public class ProfileUpdateAction extends BaseSessionAwareAction{
    private int userID;
    private String uFirstName = "";
    private String uLastName = "";
    private String uEmail = "";
    private String uPhone = "";

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();

        if (sm.isLoggedIn(userSessionObject)) {
            UserBean currentUser = sm.getUserBean(userSessionObject);
            UserBean updatingUser = null;

            UserQuery userQuery = new UserQuery();

            //TODO validate fields

            if (currentUser.getUserIdentification() == userID || currentUser.getUserType() == UserType.Staff){
                String returnCode = ERROR;
                try {
                    if (currentUser.getUserIdentification() == userID){
                        updatingUser = currentUser;
                    }else{
                        updatingUser = userQuery.userDetailsQuery(userID);
                    }
                    if (updatingUser != null){
                        userQuery.userDetailsUpdate(userID, uFirstName, uLastName, uEmail, uPhone);

                        updatingUser.setUserFirstName(uFirstName);
                        updatingUser.setUserLastName(uLastName);
                        updatingUser.setUserEmail(uEmail);
                        updatingUser.setUserPhoneNumber(uPhone);

                        returnCode = SUCCESS;
                    }
                } catch (SQLException e) {
                    //System.out.println(e.getMessage());
                }

                return returnCode;
            }else{
                return ResponseCodes.FORBIDDEN;
            }

        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public int getUserID() {
        return userID;
    }
}
