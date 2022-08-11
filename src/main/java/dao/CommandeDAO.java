package dao;

import model.Article;
import model.Client;
import model.Commande;

import java.sql.*;
import java.util.ArrayList;

public class CommandeDAO extends ACommonDAO {

    private Commande commande;
    private Client client;

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
        System.out.print("\n");
    }

    /**
     * Affichage de la liste des commandes d'un client ciblé dont le numéro de commande est supérieur à un numéro ciblé
     * @param no_client
     * @param no_commande
     */
    public void afficherCommandesClient(int no_client, int no_commande) {
        String query = "SELECT * FROM commande WHERE no_client = ? AND no_commande > ?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, no_client);
            preparedStatement.setInt(2, no_commande);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Commandes du client no " + no_client +" dont le numéro de commande est supérieur à " + no_commande);
            afficherListe(getListOfResults(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affichage des coordonnées des clients qui ont passé une commande à une date ciblée
     * @param date_commande
     */
    public void afficherClientsSelonDateCommandes(String date_commande) {
        String query = "SELECT c.no_client, c.no_telephone, d.no_commande " +
                "FROM client c JOIN commande d ON (c.no_client = d.no_client)" +
                "WHERE DATE_FORMAT(d.date_commande, '%d/%m/%Y') LIKE ?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date_commande);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Coordonnées des clients qui ont passé une commande le " + date_commande);
            afficherListeClientNoCommande(getListOfResultsClientNoCommande(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtention de la liste des clients avec leur numéro de commande de la base de données
     * @param resultSet
     * @return liste des clients avec leur numéro de commande obtenue de la base de données
     */
    public ArrayList<Object> getListOfResultsClientNoCommande(ResultSet resultSet) {
        ArrayList<Object> liste = new ArrayList<>();

        while (true) {
            try {
                while (resultSet.next()) {
                    int no_client = resultSet.getInt("no_client");
                    String no_telephone = resultSet.getString("no_telephone");
                    int no_commande = resultSet.getInt("no_commande");

                    Client client = new Client(no_client, no_telephone);
                    Commande commande = new Commande(no_commande);
                    liste.add(client);
                    liste.add(commande);
                }
                return liste;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Affichage dans la console de la liste des clients avec leur numéro de commande
     * @param liste
     */
    public void afficherListeClientNoCommande(ArrayList<Object> liste) {
        System.out.printf("\t %-15s | %-20s | %-15s\n", "No client", "No telephone", "No commande");
        for (Object object: liste) {
            if (object instanceof Client) {
                System.out.printf("\t %-15d | ", ((Client) object).getNo_client());
                System.out.printf("%-20s | ", ((Client) object).getNo_telephone());
            } else if (object instanceof Commande) {
                System.out.print(((Commande) object).getNo_commande());
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
