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

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
	    String url = "jdbc:mysql://localhost:3306/vente";
        String username = "root";
        String password = "^5R7&R737#MJ8&B!*r$qF&46";

//        ArticleDAO articleDAO = new ArticleDAO(url, username, password);
//        articleDAO.insererLigne(new Article(85, "Dattier", 31.99, 10));
//
//        ClientDAO clientDAO = new ClientDAO(url, username, password);
//        clientDAO.insererLigne(new Client(90, "Jean Dupond", "(222)222-2222"));
//
//        CommandeDAO commandeDAO = new CommandeDAO(url, username, password);
//        commandeDAO.insererLigne(new Commande(9, "2000-07-16", 90));

        LivraisonDAOImpl livraisonDAO = new LivraisonDAOImpl();
        try {
            livraisonDAO.getLivraisonByNoLivraison(db.connectToDB(url, username, password), 100);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Livraison livraisonTest = new Livraison(999, LocalDate.now());

        try {
            livraisonDAO.insertLivraison(db.connectToDB(url, username, password), livraisonTest);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
