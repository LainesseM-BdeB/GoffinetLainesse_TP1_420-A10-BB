package dao;

import model.DetailLivraison;
import model.Livraison;

import java.sql.*;
import java.util.ArrayList;

public class DetailLivraisonDAO extends ACommonDAO{

    public DetailLivraisonDAO(Connection connection) {
        super(connection);
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
                    "INSERT INTO detail_livraison (no_livraison, no_commande, no_article, quantite_livree) VALUES (?,?,?,?);"
            );
            preparedStatement.setInt(1, detailLivraison.getNoLivraison());
            preparedStatement.setInt(2, detailLivraison.getNoCommande());
            preparedStatement.setInt(3, detailLivraison.getNoArticle());
            preparedStatement.setInt(4, detailLivraison.getQuantiteLivree());

            preparedStatement.executeUpdate();
            System.out.println("DetailLivraison insérée.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object findByID(int id) {
        String query = "SELECT * FROM detail_livraison WHERE no_livraison = " + id;
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

    public ArrayList<DetailLivraison> findByIDCommande(int id) {
        ArrayList<DetailLivraison> detailLivraisons = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT no_livraison, no_commande, no_article, quantite_livree FROM detail_livraison WHERE no_commande = ?;"
            );
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();


            while (result.next()) {
                DetailLivraison detailLivraison = new DetailLivraison();
                detailLivraison.setNoLivraison(result.getInt(1));
                detailLivraison.setNoCommande(result.getInt(2));
                detailLivraison.setNoArticle(result.getInt(3));
                detailLivraison.setQuantiteLivree(result.getInt(4));

                detailLivraisons.add(detailLivraison);
            }

        } catch (SQLException e) {
            System.out.println("Aucune Livraison n'existe");
        }

        return detailLivraisons;
    }

    @Override
    public ArrayList getListOfResults(ResultSet resultSet) {
        return null;
    }

    @Override
    public void afficherListe(ArrayList liste) {

    }
}
