package org.a3.queries;

import org.a3.beans.UserBean;
import org.a3.beans.UserType;

import java.sql.*;

public class UserQuery {
    public UserBean loginQuery(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://seng2050-a3.database.windows.net:1433;databaseName=SENG2050_A3",
                "seng2050_admin",
                "hf%yY6u*i7(Py@");
        //TODO replace

        String loginQuery = "SELECT id,firstName,lastName,userRole FROM SENG2050_A3.dbo.Users WHERE userName=? AND userpassword=?";
        PreparedStatement statement = conn.prepareStatement(loginQuery);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet loginResult = statement.executeQuery();
        System.out.println("user: " + username);
        int rowNum = 0;

        UserBean result = null;
        if (loginResult.next()){
            result = new UserBean();
            result.setUserIdentification(loginResult.getInt(1));
            result.setUserFirstName(loginResult.getString(2));
            result.setUserLastName(loginResult.getString(3));

            int role = loginResult.getInt(4);
            result.setUserType(UserType.values()[role - 1]);
        }

        statement.close();
        conn.close();

        return result;
    }
    public UserBean userDetailsQuery(int userID) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://seng2050-a3.database.windows.net:1433;databaseName=SENG2050_A3",
                "seng2050_admin",
                "hf%yY6u*i7(Py@");

        String userQuery = "SELECT firstName,lastName,email,phoneNumber FROM SENG2050_A3.dbo.Users WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(userQuery);
        statement.setInt(1, userID);

        ResultSet queryResult = statement.executeQuery();
        UserBean resultBean = null;
        if (queryResult.next()){
            resultBean = new UserBean();
            resultBean.setUserFirstName(queryResult.getString(1));
            resultBean.setUserLastName(queryResult.getString(2));
            resultBean.setUserEmail(queryResult.getString(3));
            resultBean.setUserPhoneNumber(queryResult.getString(4));
        }

        queryResult.close();
        conn.close();

        return resultBean;
    }
    public void userDetailsUpdate(int userID, String nFirstName, String nLastName, String nEmail, String nPhone) throws SQLException {
        //if userDetailsUpdate works, we'll update the bean on our end
        Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://seng2050-a3.database.windows.net:1433;databaseName=SENG2050_A3",
                "seng2050_admin",
                "hf%yY6u*i7(Py@");

        String updateQuery = "UPDATE SENG2050_A3.dbo.Users SET firstName=?,lastName=?,email=?,phoneNumber=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(updateQuery);
        statement.setString(1, nFirstName);
        statement.setString(2, nLastName);
        statement.setString(3, nEmail);
        statement.setString(4, nPhone);
        statement.setInt(5, userID);

        statement.executeUpdate();
    }
    public void createUser(String uFirstName, String uLastName, String uEmail, String uPhone) throws SQLException{
        //STUB!
    }
}

