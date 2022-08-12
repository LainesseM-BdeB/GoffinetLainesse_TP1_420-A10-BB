package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class db {

    private static Connection connection;

    private db() {
        try {
            String url = "jdbc:mysql://localhost:3306/vente";
            String username = "root";
            String password = "";
            connection = connectToDB(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new db();
        }
        return connection;
    }

    private static Connection connectToDB(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
