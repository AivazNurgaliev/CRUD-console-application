package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbUtilConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/Patients_base";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootPass777!";

    private static Connection connection = null;

    //Connecting to Database
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        }
        return connection;
    }

}
