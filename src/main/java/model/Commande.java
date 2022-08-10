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
    private Client client;

    public Commande() {
    }

    public Commande(int no_commande, String date_commande, int no_client) {
        this.no_commande = no_commande;
        this.date_commande = date_commande;
        this.no_client = no_client;
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

    public Client getClient() {
        return client;
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

    public void setClient(Client client) {
        this.client = client;
    }
}
