package dao;

import model.DetailLivraison;

import java.sql.*;
import java.util.ArrayList;

/**
 * Ceci est la classe DAO de "DetailLivraison"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
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
        return null;
    }

    /**
     * Cherche un détail de livraison selon son ID = no_livraison, no_commande et no_article
     * @param id - no_livraison
     * @param id2 - no_commande
     * @param id3 - no_article
     * @return Un résultat pour tout les match ou null
     */
    public Object findByNoLivraisonNoCommandeNoArticle(int id, int id2, int id3) {
        String query = "SELECT * FROM detail_livraison WHERE no_livraison = ? AND no_commande = ? AND no_article = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id2);
            preparedStatement.setInt(3, id3);

            ResultSet resultSet = preparedStatement.executeQuery();

            DetailLivraison detailLivraison = new DetailLivraison();
            resultSet.next();
            detailLivraison.setNoLivraison(resultSet.getInt(1));
            detailLivraison.setNoCommande(resultSet.getInt(2));
            detailLivraison.setNoArticle(resultSet.getInt(3));
            detailLivraison.setQuantiteLivree(resultSet.getInt(4));

            return detailLivraison;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList findAll() {
        return null;
    }

    /**
     * Function qui retourne une liste de détail de livraison pour résoudre la question b-5 en cherchant les détails de livraison selon le no_commande
     * @param id
     * @return
     */
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
    protected ArrayList getListOfResults(ResultSet resultSet) {
        return null;
    }

    @Override
    protected void afficherListe(ArrayList liste) {

    }
}
