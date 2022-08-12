package model;

import java.time.LocalDate;

/**
 * Ceci est la classe POJO de "Livraison"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */

public class Livraison {

    private int noLivraison;
    private LocalDate dateLivraison;

    public Livraison() {
    }

    public Livraison(int noLivraison, LocalDate dateLivraison) {
        this.noLivraison = noLivraison;
        this.dateLivraison = dateLivraison;
    }

    public int getNoLivraison() {
        return noLivraison;
    }

    public void setNoLivraison(int noLivraison) {
        this.noLivraison = noLivraison;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "noLivraison=" + noLivraison +
                ", dateLivraison=" + dateLivraison +
                '}';
    }
}
