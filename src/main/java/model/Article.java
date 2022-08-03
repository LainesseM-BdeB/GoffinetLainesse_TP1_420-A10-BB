package model;

/**
 * Ceci est la classe POJO de "Article"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
public class Article {

    private int no_article;
    private String description;
    private double prix_unitaire;
    private int quantite_en_stock;

    public Article() {
    }

    public int getNo_article() {
        return no_article;
    }

    public String getDescription() {
        return description;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public int getQuantite_en_stock() {
        return quantite_en_stock;
    }

    public void setNo_article(int no_article) {
        this.no_article = no_article;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public void setQuantite_en_stock(int quantite_en_stock) {
        this.quantite_en_stock = quantite_en_stock;
    }
}
