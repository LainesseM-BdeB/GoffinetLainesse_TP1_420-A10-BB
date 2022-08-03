package dao;

import model.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO {

    private String url;
    private String username;
    private String password;

    public ClientDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void insererLigne(Client client) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String requete = "INSERT INTO client VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, client.getNo_client());
            preparedStatement.setString(2, client.getNom_client());
            preparedStatement.setString(3, client.getNo_telephone());
            preparedStatement.execute();
            System.out.println(client.getNom_client() + " est maintenant sauvegardé dans la BD");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de sauvegarder");
        }
    }
}
