package org.a3.actions;

import org.a3.beans.UserType;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

public class SubmitIssueAction extends BaseSessionAwareAction{
    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            if (sm.getUserType(userSessionObject) == UserType.Staff){
                return ResponseCodes.UNAUTHORIZED;
            }else{
                /* do server-side validation */
                boolean validation = false;
                if (validation){
                    return SUCCESS;
                }else{
                    return ERROR;
                }
            }
        }
        return ResponseCodes.FORBIDDEN;
    }
}
