package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class db {

    private String url = "jdbc:mysql://localhost:3306/vente";
    private String username = "root";
    private String password = "Philosophie0883";

    private static Connection connection;

    private db() {
        try {
            connection = connectToDB(this.url, this.username, this.password);
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

    public static ArrayList<HashMap<String, String>> selectQuery(PreparedStatement query) throws SQLException{
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        ResultSet response = query.executeQuery();

        int col = response.getMetaData().getColumnCount();

        while (response.next()) {
            HashMap<String, String> line = new HashMap<>();
            for (int i = 1; i <= col; i++) {
                line.put(response.getMetaData().getColumnName(i), response.getString(i));
            }
            result.add(line);
        }
        return result;
    }

    public static void insertQuery(PreparedStatement query) throws SQLException {
        query.executeUpdate();
    }

}
