package org.a3.services;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;

import java.util.Map;

public class SessionManager {
    public static final String LOGGED_IN_KEY = "loggedIn";
    public static final String USER_BEAN_KEY = "userBean";
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
    public boolean isLoggedIn(Map<String, Object> sessionMap){
        return ((int) sessionMap.getOrDefault(LOGGED_IN_KEY, 0)) == 1;
    }
    public void setUserBean(Map<String, Object> sessionMap, UserBean bean){
        sessionMap.put(USER_BEAN_KEY, bean);
    }
    public UserBean getUserBean(Map<String, Object> sessionMap){
        return (UserBean) sessionMap.get(USER_BEAN_KEY);
    }
    public void destroySession(Map<String, Object> sessionMap){
        sessionMap.clear();
    }
}
