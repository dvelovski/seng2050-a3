package org.a3.beans;

import javax.sql.*;
import java.sql.*;
import javax.naming.*;

public class ConfigBean
{
    private static ConfigBean instance;
    private final DataSource dataSource = makeDataSource();
    static{

    }
    private DataSource makeDataSource()
    {
        try
        {
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:/comp/env/jdbc/MyConnection");
        } catch (NamingException e)
        {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    public static ConfigBean get(){
        if (instance == null) {
            instance = new ConfigBean();
        }
        return instance;
    }
}
