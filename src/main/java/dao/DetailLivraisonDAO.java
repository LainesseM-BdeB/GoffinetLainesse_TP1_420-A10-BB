package dao;

import model.DetailLivraison;
import model.Livraison;
import utils.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetailLivraisonDAO extends ACommonDAO{

    public DetailLivraisonDAO(Connection connection) {
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

        DetailLivraison detailLivraison = (DetailLivraison) object;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO detaillivraison (nolivraison, nocommande, noarticle, quantiteLivree) VALUES (?,?,?,?);"
            );
            preparedStatement.setInt(1, detailLivraison.getNoLivraison());
            preparedStatement.setInt(2, detailLivraison.getNoCommande());
            preparedStatement.setInt(3, detailLivraison.getNoArticle());
            preparedStatement.setInt(4, detailLivraison.getQuantiteLivree());

            db.insertQuery(preparedStatement);
            System.out.println("DetailLivraison Insérée.");
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
    public ArrayList<Object> findAll() {
        return null;
    }
}
