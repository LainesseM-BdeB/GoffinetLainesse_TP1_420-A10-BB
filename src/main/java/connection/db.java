package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ceci est la classe db qui sert à créer et gérer la connexion à la db."
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
public class db {

    private static Connection connection;

    private db() {
        try {
            String url = "jdbc:mysql://localhost:3306/vente";
            String username = "root";
            String password = "Philosophie0883";
            connection = DriverManager.getConnection(url, username, password);
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

}
