package dao;

import model.DetailLivraison;
import model.LigneCommande;
import utils.db;

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
    public Object create(Object object) {
        return null;
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
                    "INSERT INTO lignecommande (nocommande, noarticle, quantite) VALUES (?,?,?);"
            );
            preparedStatement.setInt(1, detailLivraison.getNoCommande());
            preparedStatement.setInt(2, detailLivraison.getNoArticle());
            preparedStatement.setInt(3, detailLivraison.getQuantite());

            db.insertQuery(preparedStatement);
            System.out.println("LigneCommande Insérée.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object findByID(int id) {
        return null;
    }

    @Override
    public Object findByName(String name) {
        return null;
    }

    @Override
    public Object findByValues(double value1, double value2) {
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
