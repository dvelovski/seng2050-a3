package org.a3.actions;

public class IndexAction extends BaseSessionAwareAction{

    @Override
    public String doExecute() {
        if (userSessionObject.get("loggedIn").equals(0)){
            return "no_login";
        }
        return "logged_in";
    }
}
