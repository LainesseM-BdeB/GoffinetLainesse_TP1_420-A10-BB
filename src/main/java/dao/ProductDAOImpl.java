package dao;

import model.Produit;

import java.sql.*;

public class ProductDAOImpl implements IProduitDao{

    private String url;
    private String username;
    private String password;

    public ProductDAOImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Produit getProduitById(long id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM produits WHERE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Produit produit = new Produit();

            while (resultSet.next()) {
                produit.setId(resultSet.getLong("id"));
                produit.setNom(resultSet.getString("nom"));
            }
            return produit;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProduit(Produit produit) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            if (produit.getId() != null) {
                String query = "UPDATE produits set nom = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, produit.getNom());
                preparedStatement.setLong(2, produit.getId());
                preparedStatement.execute();
            } else {
                String query = "INSERT produits (nom) VALUES (?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, produit.getNom());
                preparedStatement.execute();
            }
            System.out.println(produit.getNom() + " est maintenant sauvegardé dans la BD");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de sauvegarder");
        }
    }
}
