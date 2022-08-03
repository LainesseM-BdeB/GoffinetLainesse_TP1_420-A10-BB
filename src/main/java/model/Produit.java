package model;


/**
 * Ceci est une classe Pojo d'exemple
 * @author Maxime et Gaelle
 * @since 3 août 2022
 * @version 1.0.0
 */
public class Produit {

    private long id;
    private String nom;

    public Produit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
