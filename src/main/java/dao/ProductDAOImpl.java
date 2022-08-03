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
    public Produit getProduitById(long id) throws SQLException {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public void saveProduit(Produit produit) {

    }
}
