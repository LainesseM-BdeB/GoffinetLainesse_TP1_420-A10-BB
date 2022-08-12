package dao;

import model.Article;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO extends ACommonDAO {

    private Article article;

    public ArticleDAO(Connection connection) {
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
     * Mise à jour de l'objet "Article"
     * @param object article
     * @return true si l'objet a été mis à jour
     */
    @Override
    public boolean update(Object object) {

        Article article = (Article) object;
        String query = "INSERT INTO article VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, article.getNo_article());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setDouble(3, article.getPrix_unitaire());
            preparedStatement.setInt(4, article.getQuantite_en_stock());
            preparedStatement.executeUpdate();
            System.out.println("Article inséré.");
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
    public ArrayList findAll() {
        return null;
    }

    /**
     * Obtention de la liste des articles de la base de données
     * @param resultSet
     * @return liste des articles obtenus de la base de données
     */
    @Override
    public ArrayList<Object> getListOfResults(ResultSet resultSet) {
        ArrayList<Object> articles = new ArrayList<>();

        while (true) {
            try {
                while(resultSet.next()) {
                    int no_article = resultSet.getInt("no_article");
                    String description = resultSet.getString("description");
                    double prix_unitaire = resultSet.getDouble("prix_unitaire");
                    int quantite_en_stock = resultSet.getInt("quantite_en_stock");

                    Article article = new Article(no_article, description, prix_unitaire, quantite_en_stock);

                    articles.add(article);
                }
                return articles;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Affichage dans la console de la liste des articles
     * @param liste d'articles
     */
    @Override
    public void afficherListe(ArrayList liste) {
        System.out.printf("\t %-10s | %-20s | %-15s | %-10s\n", "No article", "Description", "Prix unitaire", "Quantité en stock");
        for (Object object: liste) {
            Article article = (Article) object;
            System.out.printf("\t %-10d | ", article.getNo_article());
            System.out.printf("%-20s | ", article.getDescription());
            System.out.printf("%-15s | ", article.getPrix_unitaire() + "$");
            System.out.printf("%-10d ", article.getQuantite_en_stock());
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Affichage de la liste des articles débutant par une lettre ciblée
     * @param lettre ciblée
     */
    public void afficherArticlesDebutantParLettre(String lettre) {
        String query = "SELECT * FROM article WHERE LOWER(description) LIKE LOWER(?);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lettre + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Articles débutant par la lettre \"" + lettre + "\" :");
            afficherListe(getListOfResults(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affichage de la liste des articles dont le prix est supérieur à la moyenne
     */
    public void afficherArticlesPrixSuperieurAMoyenne() {
        String query = "SELECT * FROM article WHERE prix_unitaire > (SELECT AVG(prix_unitaire) FROM article);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery(query);
            System.out.println("Articles dont le prix est supérieur à la moyenne");
            afficherListe(getListOfResults(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
