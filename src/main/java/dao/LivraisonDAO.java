package dao;

import model.Livraison;
import utils.db;

import java.sql.*;
import java.util.ArrayList;

public class LivraisonDAO extends ACommonDAO{

    public LivraisonDAO(Connection connection) {
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

        Livraison livraison = (Livraison) object;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO livraison (nolivraison, datelivraison) VALUES (?,?);"
            );
            preparedStatement.setInt(1, livraison.getNoLivraison());
            preparedStatement.setDate(2, Date.valueOf(livraison.getDateLivraison()));

            db.insertQuery(preparedStatement);
            System.out.println("Livraison Insérée.");
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

    @Override
    public ArrayList getListOfResults(ResultSet resultSet) {
        return null;
    }

    @Override
    public void afficherListe(ArrayList liste) {

    }
}
