package org.a3.services;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class JDBCUtil
{
    private static JDBCUtil instance;
    private String dbURL;
    private String dbUser;
    private String dbPass;
    public JDBCUtil(){

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

    public static JDBCUtil get(){
        if (instance == null) {
            instance = new JDBCUtil();
        }
        return instance;
    }
}
