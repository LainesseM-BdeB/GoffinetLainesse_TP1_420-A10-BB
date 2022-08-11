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
//
//        ArticleDAO articleA = new ArticleDAO(connection);
//        articleA.update(new Article(85, "Dattier", 31.99, 10));
//
//        ClientDAO clientA = new ClientDAO(connection);
//        clientA.update(new Client(90, "Jean Dupond", "(222)222-2222"));
//
//        CommandeDAO commandeA = new CommandeDAO(connection);
//        commandeA.update(new Commande(9, "2000-07-16", 90));

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


        ArticleDAO articleB = new ArticleDAO(connection);
        CommandeDAO commandeB = new CommandeDAO(connection);
        //1.
        commandeB.afficherCommandesClient(10, 5);
        //2.
        commandeB.afficherClientsSelonDateCommandes("04/06/2000");
        // 3.
        articleB.afficherArticlesDebutantParLettre("C");
        // 4.
        articleB.afficherArticlesPrixSuperieurAMoyenne();

    }
}
