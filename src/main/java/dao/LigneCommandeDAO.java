package dao;

import model.LigneCommande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Ceci est la classe DAO de "LigneCommande"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
public class LigneCommandeDAO extends ACommonDAO{

    public LigneCommandeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        LigneCommande detailLivraison = (LigneCommande) object;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ligne_commande (no_commande, no_article, quantite) VALUES (?,?,?);"
            );
            preparedStatement.setInt(1, detailLivraison.getNoCommande());
            preparedStatement.setInt(2, detailLivraison.getNoArticle());
            preparedStatement.setInt(3, detailLivraison.getQuantite());

            preparedStatement.executeUpdate();
            System.out.println("LigneCommande insérée.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object findByID(int id) {
        String query = "SELECT * FROM ligne_commande WHERE no_commande = " + id;
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

    /**
     * Cherche un détail de ligne selon son ID = no_commande et no_article
     * @param id - no_commande
     * @param id2 - no_article
     * @return Un résultat pour tout les match ou null
     */
    public Object findByNoCommandeNoArticle(int id, int id2) {
        String query = "SELECT * FROM detail_livraison WHERE no_commande = ? AND no_article = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList findAll() {
        return null;
    }

    @Override
    protected ArrayList getListOfResults(ResultSet resultSet) {
        return null;
    }

    @Override
    protected void afficherListe(ArrayList liste) {

    }
}
