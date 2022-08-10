package dao;

import model.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDAO extends ACommonDAO {

    public ClientDAO(Connection connection) {
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

    /**
     * Mise à jour de l'objet "Client"
     * @param object article
     * @return true si l'objet a été mis à jour
     */@Override
    public boolean update(Object object) {

        Client client = (Client) object;
        String query = "INSERT INTO client VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, client.getNo_client());
            preparedStatement.setString(2, client.getNom_client());
            preparedStatement.setString(3, client.getNo_telephone());
            preparedStatement.executeUpdate();
            System.out.println("Client inséré.");
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
