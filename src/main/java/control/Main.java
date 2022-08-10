package control;

import dao.ArticleDAO;
import dao.ClientDAO;
import dao.CommandeDAO;
import dao.LivraisonDAOImpl;
import model.Article;
import model.Client;
import model.Commande;
import model.Livraison;
import utils.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Cette application permet de manipuler les données des tables de la base de données "ventes"
 * @author Maxime Lainesse, Gaëlle Goffinet
 * @since 2022/08/03
 */
public class Main {

    public static void main(String[] args) {
        Connection connection = db.getConnection();

        // Tests d'insertion pour chacune des tables (a.)

        ArticleDAO articleDAO = new ArticleDAO(connection);
        articleDAO.update(new Article(85, "Dattier", 31.99, 10));

        ClientDAO clientDAO = new ClientDAO(connection);
        clientDAO.update(new Client(90, "Jean Dupond", "(222)222-2222"));

        CommandeDAO commandeDAO = new CommandeDAO(connection);
        commandeDAO.update(new Commande(9, "2000-07-16", 90));

//        LivraisonDAOImpl livraisonDAO = new LivraisonDAOImpl();
//        try {
//            livraisonDAO.getLivraisonByNoLivraison(db.getConnection(), 100);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Livraison livraisonTest = new Livraison(999, LocalDate.now());
//
//        try {
//            livraisonDAO.insertLivraison(db.getConnection(), livraisonTest);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // Tests de sélection (b.)

    }
}
