package org.a3.beans;

import java.io.Serializable;
public class UserBean implements Serializable {
    
    private String userEmail;
    private String userPassword;
    private String userLastName;
    private String userFirstName;
    private String userPhoneNumber;
    private int userIdentification;
    private UserType userType;

    public UserBean() {

        this.userEmail = null;
        this.userPassword = null;
        this.userLastName = null;
        this.userFirstName = null;
        this.userPhoneNumber = null;
        this.userIdentification = 0;
        this.userType = null;
    }

    public void setUserEmail (String newUserEmail) {
        this.userEmail = newUserEmail;
    }

    public void setUserPassword (String newUserPassword) {
        this.userPassword = newUserPassword;
    }

    public void setUserLastName (String newUserLastName) {
        this.userLastName = newUserLastName;
    }

    public void setUserFirstName (String newUserFirstName) { 
        this.userFirstName = newUserFirstName; 
    }
    
    public void setUserPhoneNumber (String newUserPhoneNumber) {
        this.userPhoneNumber = newUserPhoneNumber;
    }

    public void setUserIdentification (int newUserIdentification) {
        this.userIdentification = newUserIdentification;
    }

    public void setStaffRoleOrPosition (UserType newUserType) {
        this.userType = newUserType;
    }

    public String getUserEmail () {
        return userEmail;
    }

    public String getUserPassword () {
        return userPassword;
    }

    public String getUserLastName () {
        return userLastName;
    }

    public String getUserFirstName () { 
        return userFirstName; 
    }

    public String getUserPhoneNumber () {
        return userPhoneNumber;
    }

    public int getUserIdentification () {
        return userIdentification;
    }

    public UserType getStaffRoleOrPosition () {
        return userType;
    }
}
