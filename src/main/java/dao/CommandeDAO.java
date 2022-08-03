package dao;

import model.Commande;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandeDAO {

    private String url;
    private String username;
    private String password;

    public CommandeDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void insererLigne(Commande commande) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String requete = "INSERT INTO commande VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, commande.getNo_commande());
            preparedStatement.setString(2, commande.getDate_commande());
            preparedStatement.setInt(3, commande.getNo_client());
            preparedStatement.execute();
            System.out.println(commande.getNo_commande() + " est maintenant sauvegardé dans la BD");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de sauvegarder");
        }
    }
}
