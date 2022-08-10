package dao;

import model.Commande;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeDAO extends ACommonDAO {

    public CommandeDAO(Connection connection) {
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
        Commande commande = (Commande) object;
        String query = "INSERT INTO commande VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commande.getNo_commande());
            preparedStatement.setString(2, commande.getDate_commande());
            preparedStatement.setInt(3, commande.getNo_client());
            preparedStatement.executeUpdate();
            System.out.println("Commande insérée.");
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
}
