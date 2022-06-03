package org.a3.actions;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

public class CreateIssueAction extends BaseSessionAwareAction{

    private String actionError;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean user = sm.getUserBean(userSessionObject);
            if (user.getUserType() == UserType.Staff){
                return ResponseCodes.FORBIDDEN;
            }else{
                return SUCCESS;
            }
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public void setReportID(String reportID) {
    }

    public String getActionError() {
        return actionError;
    }

    public void setActionError(String actionError) {
        this.actionError = actionError;
    }
}
