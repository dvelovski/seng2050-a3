package org.a3.queries;

import org.a3.services.JDBCUtil;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.services.PasswordGenerator;

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
        try (Connection conn = JDBCUtil.get().createConnection()) {
            String userQuery = "SELECT userName,firstName,lastName,email,phoneNumber FROM SENG2050_A3.dbo.Users WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(userQuery);
            statement.setInt(1, userID);

            ResultSet queryResult = statement.executeQuery();
            UserBean resultBean = null;
            if (queryResult.next()){
                resultBean = new UserBean();
                resultBean.setUserName(queryResult.getString(1));
                resultBean.setUserFirstName(queryResult.getString(2));
                resultBean.setUserLastName(queryResult.getString(3));
                resultBean.setUserEmail(queryResult.getString(4));
                resultBean.setUserPhoneNumber(queryResult.getString(5));
            }

            return resultBean;
        }
    }
    public void userDetailsUpdate(int userID, String nFirstName, String nLastName, String nEmail, String nPhone) throws SQLException {
        //if userDetailsUpdate works, we'll update the bean on our end
        try (Connection conn = JDBCUtil.get().createConnection()) {
            String updateQuery = "UPDATE SENG2050_A3.dbo.Users SET firstName=?,lastName=?,email=?,phoneNumber=? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setString(1, nFirstName);
            statement.setString(2, nLastName);
            statement.setString(3, nEmail);
            statement.setString(4, nPhone);
            statement.setInt(5, userID);

            statement.executeUpdate();
        }
    }
    public String[] createUser(String uFirstName, String uLastName, String uEmail, String uPhone, int userType) throws SQLException{
        //STUB!
        String newUserName = (uLastName.length() < 5 ? uLastName : uLastName.substring(0, 5)); //if there are less than 5 characters in the surname, start with that
        newUserName += (uFirstName.length() < 3 ? uFirstName : uFirstName.substring(0, 3)); //if there are less than 3 characters in first name, append the whole thing
        //gives us minimum of 8 characters' length BEFORE accounting for any existing users with the same name. if there are existing users, add a counter and increse it until there are no matches

        String finalPassword = PasswordGenerator.get().generatePassword(8);

        //System.out.println("generated username: " + newUserName + " / pass: " + finalPassword);

        try (Connection conn = JDBCUtil.get().createConnection()){
            //firstly check for username conflicts
            String testUserName = newUserName;

            int countOfExistingUsers = 0;
            int appendedCounter = 0;

            String userNameQuery = "SELECT COUNT(1) AS uCount FROM Users WHERE userName LIKE ?";
            PreparedStatement nameQueryStatement = conn.prepareStatement(userNameQuery,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            do{
                nameQueryStatement.setString(1, testUserName);
                nameQueryStatement.executeQuery();

                ResultSet queryRes = nameQueryStatement.getResultSet();
                if (queryRes.first()){
                    countOfExistingUsers = queryRes.getInt(1);
                }else{
                    countOfExistingUsers = 0;
                }

                //System.out.println("count for " + testUserName + " is " + countOfExistingUsers);

                if (countOfExistingUsers > 0){
                    testUserName = newUserName + (++appendedCounter);
                }
            }while (countOfExistingUsers > 0);

            String finalUserName = (appendedCounter == 0 ? newUserName : testUserName);
            //System.out.println("The final user name is: " + testUserName);

            String creationQuery = "INSERT INTO Users (userName, firstName, lastName, email, phoneNumber, userpassword, userRole) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(creationQuery);
            insertStatement.setString(1, finalUserName);
            insertStatement.setString(2, uFirstName);
            insertStatement.setString(3, uLastName);
            insertStatement.setString(4, uEmail);
            insertStatement.setString(5, uPhone);
            insertStatement.setString(6, finalPassword);
            insertStatement.setInt(7, userType);

            insertStatement.execute();

            return new String[]{finalUserName, finalPassword};
        }
    }
}

