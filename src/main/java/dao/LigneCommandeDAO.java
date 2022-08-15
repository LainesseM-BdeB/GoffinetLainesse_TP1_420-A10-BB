package dao;

import model.LigneCommande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LigneCommandeDAO extends ACommonDAO{

    public LigneCommandeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        LigneCommande detailLivraison = (LigneCommande) object;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ligne_commande (no_commande, no_article, quantite) VALUES (?,?,?);"
            );
            preparedStatement.setInt(1, detailLivraison.getNoCommande());
            preparedStatement.setInt(2, detailLivraison.getNoArticle());
            preparedStatement.setInt(3, detailLivraison.getQuantite());

            preparedStatement.executeUpdate();
            System.out.println("LigneCommande insérée.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object findByID(int id) {
        String query = "SELECT * FROM ligne_commande WHERE no_commande = " + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList findAll() {
        return null;
    }

    @Override
    public ArrayList getListOfResults(ResultSet resultSet) {
        return null;
    }

    @Override
    public void afficherListe(ArrayList liste) {

    }
}
