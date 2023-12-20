package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/SVHCMUTE?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
