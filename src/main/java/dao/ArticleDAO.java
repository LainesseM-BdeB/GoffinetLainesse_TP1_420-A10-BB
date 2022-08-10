package dao;

import model.Article;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO extends ACommonDAO {

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

    @Override
    public boolean update(Object object) {

        Article article = (Article) object;
        String query = "INSERT INTO article VALUES (?, ?, ?, ?)";
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
