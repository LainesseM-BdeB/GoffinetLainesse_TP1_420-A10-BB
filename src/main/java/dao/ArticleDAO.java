package dao;

import model.Article;

import java.sql.*;

public class ArticleDAO {
    private String url;
    private String username;
    private String password;

    public ArticleDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void insererLigne(Article article) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String requete = "INSERT INTO article VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, article.getNo_article());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setDouble(3, article.getPrix_unitaire());
            preparedStatement.setInt(4, article.getQuantite_en_stock());
            preparedStatement.execute();
            System.out.println(article.getDescription() + " est maintenant sauvegardé dans la BD");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de sauvegarder");
        }
    }

}
