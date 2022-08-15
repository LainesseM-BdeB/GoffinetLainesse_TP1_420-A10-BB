package control;

import dao.*;
import model.*;
import utils.db;

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

        ArticleDAO articleA = new ArticleDAO(connection);
        articleA.update(new Article(85, "Dattier", 31.99, 10));

        ClientDAO clientA = new ClientDAO(connection);
        clientA.update(new Client(90, "Jean Dupond", "(222)222-2222"));

        CommandeDAO commandeA = new CommandeDAO(connection);
        commandeA.update(new Commande(9, "2000-07-16", 90));

        // Test pour l'insertion d'une nouvelle livraison dans la base de données
        LivraisonDAO livraisonDAO = new LivraisonDAO(connection);
        Livraison livraisonTestInsertion = new Livraison(999, LocalDate.now());
        livraisonDAO.update(livraisonTestInsertion);
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(livraisonDAO.findByID(999));

        // Test pour l'insertion d'une nouvelle ligne de commande
        LigneCommandeDAO ligneCommandeDAO = new LigneCommandeDAO(connection);
        LigneCommande ligneCommandeTestInsertion = new LigneCommande(999, 1, 999);
        ligneCommandeDAO.update(ligneCommandeTestInsertion);
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(ligneCommandeDAO.findByID(999));

        // Test pour l'insertion d'un nouveau détail de livraison
        DetailLivraisonDAO detailLivraisonDAO = new DetailLivraisonDAO(connection);
        DetailLivraison detailLivraisonTestInsertion = new DetailLivraison(999, 1, 1, 999);
        detailLivraisonDAO.update(detailLivraisonTestInsertion);
        //Recherche dans la BD la nouvelle insertion et affiche les informations si elle la trouve.
        System.out.println(detailLivraisonDAO.findByID(999));


        // Tests de sélection (b.)
        ArticleDAO articleB = new ArticleDAO(connection);
        CommandeDAO commandeB = new CommandeDAO(connection);
        ClientDAO clientB = new ClientDAO(connection);
        //1.
        commandeB.afficherCommandesClient(10, 5);
        //2.
        commandeB.afficherClientsSelonDateCommandes("04/06/2000");
        //3.
        articleB.afficherArticlesDebutantParLettre("C");
        //4.
        articleB.afficherArticlesPrixSuperieurAMoyenne();
        //5.
        commandeB.afficherListeCommandeAvecNoLivraison();
        //6.
        clientB.afficherListeClientSansCommande();
    }
}
