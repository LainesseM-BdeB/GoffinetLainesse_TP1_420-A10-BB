package model;

/**
 * Ceci est la classe POJO de "Commande"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
public class Commande {

    private int no_commande;
    private String date_commande;
    private int no_client;

    public Commande() {
    }

    public int getNo_commande() {
        return no_commande;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public int getNo_client() {
        return no_client;
    }

    public void setNo_commande(int no_commande) {
        this.no_commande = no_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public void setNo_client(int no_client) {
        this.no_client = no_client;
    }
}