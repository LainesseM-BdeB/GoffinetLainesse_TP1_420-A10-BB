package control;

import dao.ArticleDAO;
import dao.ClientDAO;
import dao.CommandeDAO;
import model.Article;
import model.Client;
import model.Commande;

public class Main {

    public static void main(String[] args) {
	    String url = "jdbc:mysql://localhost:3306/vente";
        String username = "root";
        String password = "";

        ArticleDAO articleDAO = new ArticleDAO(url, username, password);
        articleDAO.insererLigne(new Article(85, "Dattier", 31.99, 10));

        ClientDAO clientDAO = new ClientDAO(url, username, password);
        clientDAO.insererLigne(new Client(90, "Jean Dupond", "(222)222-2222"));

        CommandeDAO commandeDAO = new CommandeDAO(url, username, password);
        commandeDAO.insererLigne(new Commande(9, "2000-07-16", 90));
    }
}
