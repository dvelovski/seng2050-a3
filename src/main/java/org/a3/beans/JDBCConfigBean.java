package org.a3.beans;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class JDBCConfigBean
{
    private static JDBCConfigBean instance;
    private String dbURL;
    private String dbUser;
    private String dbPass;
    public JDBCConfigBean(){

    }
    public void loadConfig(ServletContext context){
        System.out.println(context.getResourcePaths("/"));

        try (InputStream iStream = context.getResourceAsStream("/WEB-INF/db.txt")){
            BufferedReader in = new BufferedReader(new InputStreamReader(iStream));
            dbURL = in.readLine();
            dbUser = in.readLine();
            dbPass = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection createConnection() throws SQLException
    {
        return DriverManager.getConnection(
                dbURL,
                dbUser,
                dbPass);
    }

    public static JDBCConfigBean get(){
        if (instance == null) {
            instance = new JDBCConfigBean();
        }
        return instance;
    }
}
