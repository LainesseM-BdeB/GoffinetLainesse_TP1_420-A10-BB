package dao;

import model.Client;
import model.Commande;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ClientDAO extends ACommonDAO {

    public ClientDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    /**
     * Mise à jour de l'objet "Client"
     * @param object article
     * @return true si l'objet a été mis à jour
     */
    @Override
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
        String query = "SELECT * FROM client WHERE no_client = " + id;
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
    public ArrayList<Client> findAll() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT no_client, nom_client, no_telephone FROM client;"
            );

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Client client = new Client(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3)
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    // pour question b-6
    public HashSet<Client> getListeClientSansCommande() {
         CommandeDAO commandeDAO = new CommandeDAO(connection);
         ArrayList<Client> clients = this.findAll();
         ArrayList<Commande> commandes = commandeDAO.findAll();

        HashSet<Client> clientsSansCommande = new HashSet<>();
        HashSet<Integer> noClientAvecCommande = new HashSet<>();

        for (Commande commande : commandes) {
            noClientAvecCommande.add(commande.getNo_client());
        }

        for (Client client : clients) {
            if (!noClientAvecCommande.contains(client.getNo_client())) {
                clientsSansCommande.add(client);
            }
        }
        return clientsSansCommande;
    }

    // pour question b-6
    public void afficherListeClientSansCommande() {
         HashSet<Client> clientsSansCommande = this.getListeClientSansCommande();

        System.out.println("\nListe des clients sans commande (no_client, no_telephone) :");
         for (Client client : clientsSansCommande) {
             System.out.printf("Client #%d - %s\n", client.getNo_client(), client.getNo_telephone());
         }
    }

    /**
     * Obtention de la liste des clients de la base de données
     * @param resultSet
     * @return liste des clients obtenus de la base de données
     */
    @Override
    public ArrayList getListOfResults(ResultSet resultSet) {
        ArrayList<Object> clients = new ArrayList<>();

        while (true) {
            try {
                while(resultSet.next()) {
                    int no_client = resultSet.getInt("no_client");
                    String nom_client = resultSet.getString("nom_client");
                    String no_telephone = resultSet.getString("no_telephone");

                    Client client = new Client(no_client, nom_client, no_telephone);

                    clients.add(client);
                }
                return clients;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Affichage dans la console de la liste des clients
     * @param liste des clients
     */
    @Override
    public void afficherListe(ArrayList liste) {
        System.out.printf("\t %-10s | %-20s | %-20s\n", "No client", "Nom client", "No téléphone");
        for (Object object: liste) {
            Client client = (Client) object;
            System.out.printf("\t %-10d | ", client.getNo_client());
            System.out.printf("%-20s | ", client.getNom_client());
            System.out.printf("%-20s ", client.getNo_telephone());
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
