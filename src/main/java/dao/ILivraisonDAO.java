package dao;

import model.Livraison;

import java.sql.Connection;
import java.sql.SQLException;

public interface ILivraisonDAO {

    void insertLivraison(Connection conn, Livraison livraison) throws SQLException;

    void getLivraisonByNoLivraison(Connection conn, int id) throws SQLException;

}
