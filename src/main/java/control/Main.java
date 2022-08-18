package control;

import dao.*;
import model.*;
import connection.db;

import java.sql.Connection;
import java.time.LocalDate;

/**
 * Cette application permet de manipuler les données des tables de la base de données "ventes"
 * @author Maxime Lainesse, Gaëlle Goffinet
 * @since 2022/08/03
 */
public class Main {

    public static void main(String[] args) {
        Connection connection = db.getConnection();

        //Tests d'insertion pour chacune des tables (a.)

        // Test pour l'insertion d'un nouvel article dans la base de données
        ArticleDAO articleDAO = new ArticleDAO(connection);
        articleDAO.update(new Article(999, "XXXXXX", 999.99, 999));
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(articleDAO.findByID(999) + "\n");

        // Test pour l'insertion d'un nouveau client dans la base de données
        ClientDAO clientDAO = new ClientDAO(connection);
        clientDAO.update(new Client(999, "John Smith", "(555)555-5555"));
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(clientDAO.findByID(999) + "\n");

        // Test pour l'insertion d'une nouvelle commande dans la base de données
        CommandeDAO commandeDAO = new CommandeDAO(connection);
        commandeDAO.update(new Commande(999, "2000-07-16", 999));
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(commandeDAO.findByID(999) + "\n");

        // Test pour l'insertion d'une nouvelle ligne de commande
        LigneCommandeDAO ligneCommandeDAO = new LigneCommandeDAO(connection);
        LigneCommande ligneCommandeTestInsertion = new LigneCommande(999, 999, 999);
        ligneCommandeDAO.update(ligneCommandeTestInsertion);
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(ligneCommandeDAO.findByNoCommandeNoArticle(999, 999) + "\n");

        // Test pour l'insertion d'une nouvelle livraison dans la base de données
        LivraisonDAO livraisonDAO = new LivraisonDAO(connection);
        Livraison livraisonTestInsertion = new Livraison(999, LocalDate.now());
        livraisonDAO.update(livraisonTestInsertion);
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(livraisonDAO.findByID(999) + "\n");

        // Test pour l'insertion d'un nouveau détail de livraison
        DetailLivraisonDAO detailLivraisonDAO = new DetailLivraisonDAO(connection);
        DetailLivraison detailLivraisonTestInsertion = new DetailLivraison(999, 999, 999, 999);
        detailLivraisonDAO.update(detailLivraisonTestInsertion);
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(detailLivraisonDAO.findByNoLivraisonNoCommandeNoArticle(999, 999, 999) + "\n");


        // Tests de sélection (b.)
        //1.
        commandeDAO.afficherCommandesClient(10, 5);
        //2.
        commandeDAO.afficherClientsSelonDateCommandes("04/06/2000");
        //3.
        articleDAO.afficherArticlesDebutantParLettre("C");
        //4.
        articleDAO.afficherArticlesPrixSuperieurAMoyenne();
        //5.
        commandeDAO.afficherListeCommandeAvecNoLivraison();
        //6.
        clientDAO.afficherListeClientSansCommande();
    }
}
