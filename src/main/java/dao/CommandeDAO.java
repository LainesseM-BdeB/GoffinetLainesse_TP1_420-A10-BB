package dao;

import model.Article;
import model.Client;
import model.Commande;

import java.sql.*;
import java.util.ArrayList;

public class CommandeDAO extends ACommonDAO {
// référence à la clé étrangère
    private Commande commande;
    private Client client; // jointure: faire référence à la classe client à partir de cette classe

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

    /**
     * Mise à jour de l'objet "Commande"
     * @param object commande
     * @return true si l'objet a été mis à jour
     */
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

    /**
     * Obtention de la liste des commandes de la base de données
     * @param resultSet
     * @return liste des commandes obtenues de la base de données
     */
    @Override
    public ArrayList getListOfResults(ResultSet resultSet) {
        ArrayList<Object> commandes = new ArrayList<>();

        while (true) {
            try {
                while(resultSet.next()) {
                    int no_commande = resultSet.getInt("no_commande");
                    String date_commande = resultSet.getString("date_commande");
                    int no_client = resultSet.getInt("no_client");

                    Commande commande = new Commande(no_commande, date_commande, no_client);

                    commandes.add(commande);
                }
                return commandes;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Affichage dans la console de la liste des commandes
     * @param liste des commandes
     */
    @Override
    public void afficherListe(ArrayList liste) {
        System.out.printf("\t %-10s | %-20s | %-10s\n", "No commande", "Date commande", "No client");
        for (Object object: liste) {
            Commande commande = (Commande) object;
            System.out.printf("\t %-10d | ", commande.getNo_commande());
            System.out.printf("%-20s | ", commande.getDate_commande());
            System.out.printf("%-10d ", commande.getNo_client());
            System.out.print("\n");
        }
    }
}
