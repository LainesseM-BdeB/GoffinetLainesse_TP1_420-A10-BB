package dao;

import model.Client;
import model.Commande;
import model.DetailLivraison;
import model.Livraison;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class CommandeDAO extends ACommonDAO {

    public CommandeDAO(Connection connection) {
        super(connection);
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

    /**
     * Recherche une commande dans la table selon sa clé primaire
     * @param id
     * @return la commande si elle existe dans la table
     */
    @Override
    public Object findByID(int id) {
        String query = "SELECT * FROM commande WHERE no_commande = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int no_commande = resultSet.getInt("no_commande");
            String date_commande = resultSet.getString("date_commande");
            int no_client = resultSet.getInt("no_client");

            return new Commande(no_commande, date_commande, no_client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtention de la liste de toutes les commandes de la table "commande"
     * @return liste des commandes de la table "commande"
     */
    @Override
    public ArrayList<Commande> findAll() {
        ArrayList<Commande> commandes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT no_commande, date_commande, no_client FROM commande;"
            );

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Commande commande = new Commande(
                        result.getInt(1),
                        result.getDate(2).toString(),
                        result.getInt(3)
                );
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commandes;
    }

    /**
     * Question b-5: Function qui affichera tout les numéros de livraison ordonné par numéro de commande.
     */
    public void afficherListeCommandeAvecNoLivraison() {
        ArrayList<Commande> commandes = this.findAll();
        DetailLivraisonDAO detailLivraisonDAO = new DetailLivraisonDAO(connection);

        System.out.println("Liste des commandes et de leur numéro de livraison si applicable :");

        for (Commande commande : commandes) {
            ArrayList<DetailLivraison> detailLivraisons = detailLivraisonDAO.findByIDCommande(commande.getNo_commande());
            System.out.printf("Commande #%d - Numéros de Livraison: ", commande.getNo_commande());
            if (detailLivraisons.size() < 1) {
                System.out.print("n/a\n");
            } else {
                TreeSet<Integer> nosLivraison = new TreeSet<>();
                for (DetailLivraison detailLivraison : detailLivraisons) {
                    nosLivraison.add(detailLivraison.getNoLivraison());
                }
                for (int noLivraison : nosLivraison) {
                    System.out.printf("%d ", noLivraison);
                }
                System.out.println();
            }
        }
    }

    /**
     * Obtention de la liste des commandes de la base de données
     * @param resultSet
     * @return liste des commandes obtenues de la base de données
     */
    @Override
    protected ArrayList<Object> getListOfResults(ResultSet resultSet) {
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
    protected void afficherListe(ArrayList liste) {
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
     * Question b-1
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

            ArrayList<Object> list = getListOfResults(resultSet);

            if(list.isEmpty()) {
                System.out.println("Il n'y a pas de commande pour le client no " + no_client + " dont le numéro de commande est supérieur à " + no_commande + ".\n");
            } else {
                System.out.println("Commandes du client no " + no_client +" dont le numéro de commande est supérieur à " + no_commande + " :");
                afficherListe(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Question b-2
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

            ArrayList<Object> list = getListOfResultsClientNoCommande(resultSet);

            if(list.isEmpty()) {
                System.out.println("Il n'y a pas de commande en date du " + date_commande + ".\n");
            } else {
                System.out.println("Coordonnées des clients qui ont passé une commande le " + date_commande + " :");
                afficherListeClientNoCommande(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtention de la liste des clients avec leur numéro de commande de la base de données
     * @param resultSet
     * @return liste des clients avec leur numéro de commande obtenue de la base de données
     */
    private ArrayList<Object> getListOfResultsClientNoCommande(ResultSet resultSet) {
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
    private void afficherListeClientNoCommande(ArrayList<Object> liste) {
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
