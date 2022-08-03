package model;

/**
 * Ceci est la classe POJO de "Client"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
public class Client {
    private int no_client;
    private String nom_client;
    private String no_telephone;

    public Client() {
    }

    public int getNo_client() {
        return no_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getNo_telephone() {
        return no_telephone;
    }

    public void setNo_client(int no_client) {
        this.no_client = no_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setNo_telephone(String no_telephone) {
        this.no_telephone = no_telephone;
    }
}
