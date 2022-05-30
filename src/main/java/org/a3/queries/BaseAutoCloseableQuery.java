package org.a3.queries;

import org.a3.services.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseAutoCloseableQuery implements AutoCloseable {
    private Connection connection;
    public BaseAutoCloseableQuery(){
    }

    public Connection getConnection() throws SQLException {
        if (connection == null){
            connection = JDBCUtil.get().createConnection();
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null){
            connection.close();
        }
    }
}
