import javax.management.remote.rmi.RMIConnectionImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:sqlserver://seng2050-a3.database.windows.net:1433;databaseName=SENG2050_A3",
                    "seng2050_admin",
                    "hf%yY6u*i7(Py@");
            System.out.println(conn.getCatalog());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
