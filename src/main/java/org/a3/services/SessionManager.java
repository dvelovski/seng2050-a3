package org.a3.services;

import org.a3.UserType;

import java.util.Map;

public class SessionManager {
    public static final String LOGGED_IN_KEY = "loggedIn";
    public static final String USER_TYPE_KEY = "userType";
    private static SessionManager manager;
    public static SessionManager get(){
        if (manager == null) {
            manager = new SessionManager();
        }
        return manager;
    }
    public void ensureSession(Map<String, Object> sessionMap){
        if (sessionMap.isEmpty()){
            createSessionMinimumData(sessionMap);
        }
    }
    public void createSessionMinimumData(Map<String, Object> sessionMap){
        sessionMap.put(LOGGED_IN_KEY, 0);
    }
    public void setLoggedIn(Map<String, Object> sessionMap, boolean loginStatus){
        sessionMap.put(LOGGED_IN_KEY, (loginStatus ? 1 : 0));
    }
    public void setUserType(Map<String, Object> sessionMap, UserType uType){
        sessionMap.put(USER_TYPE_KEY, uType);
    }
    public boolean isLoggedIn(Map<String, Object> sessionMap){
        return ((int) sessionMap.getOrDefault(LOGGED_IN_KEY, 0)) == 1;
    }
    public void destroySession(Map<String, Object> sessionMap){
        sessionMap.clear();
    }
}
