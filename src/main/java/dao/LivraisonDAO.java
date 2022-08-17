package dao;

import model.Livraison;

import java.sql.*;
import java.util.ArrayList;

/**
 * Ceci est la classe DAO de "Livraison"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
public class LivraisonDAO extends ACommonDAO{

    public LivraisonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean delete(Object object) {
        Livraison livraison = (Livraison) object;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM livraison WHERE no_livraison = ?;"
            );
            preparedStatement.setInt(1, livraison.getNoLivraison());

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                if (rows == 0) {
                    throw new SQLDataException("There were no row deleted.\n");
                } else {
                    throw new SQLDataException("There was too many rows deleted.\n" + rows + " rows were deleted.");
                }
            }

            System.out.printf("La livraison #%s à été retirée.\n", livraison.getNoLivraison());
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        Livraison livraison = (Livraison) object;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO livraison (no_livraison, date_livraison) VALUES (?,?);"
            );
            preparedStatement.setInt(1, livraison.getNoLivraison());
            preparedStatement.setDate(2, Date.valueOf(livraison.getDateLivraison()));

            preparedStatement.executeUpdate();
            System.out.printf("Livraison #%s insérée.\n", livraison.getNoLivraison());
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Object findByID(int id) {
        Livraison livraison = new Livraison();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT no_livraison, date_livraison FROM livraison WHERE no_livraison = ?;"
            );
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            livraison.setNoLivraison(result.getInt(1));
            livraison.setDateLivraison(result.getDate(2).toLocalDate());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livraison;
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
