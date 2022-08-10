package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class db {

    public static Connection connectToDB(String url,String user,String password) throws SQLException {
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
