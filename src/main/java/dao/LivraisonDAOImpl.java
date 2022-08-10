package dao;

import model.Livraison;
import utils.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LivraisonDAOImpl implements ILivraisonDAO{

    @Override
    public void insertLivraison(Connection conn, Livraison livraison) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO livraison (nolivraison, datelivraison) VALUES (?,?);"
        );

        preparedStatement.setInt(1, livraison.getNoLivraison());
        preparedStatement.setDate(2, Date.valueOf(livraison.getDateLivraison()));

        db.insertQuery(preparedStatement);
        System.out.println("Livraison Insérée.");
    }

    @Override
    public void getLivraisonByNoLivraison(Connection conn, int noLivraison) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM livraison WHERE noLivraison = ?;"
        );

        preparedStatement.setInt(1, noLivraison);

        ArrayList<HashMap<String, String>> result = db.selectQuery(preparedStatement);

        for (HashMap<String, String> cols : result) {
            int i = 1;
            for (String key : cols.keySet()) {
                System.out.println("ln-" + i + ": " + key + "=" + cols.get(key));
            }
        }
    }
}
