package dao;

import model.Produit;

import java.sql.SQLException;

public interface IProduitDao {


    /**
     * Faire une recherche par ID
     * @param id id du produit à rechercher
     * @return objet Produit du produit rechercher
     */
    Produit getProduitById(long id);

    /**
     * Sauvegarde un produit dans la BD
     * @param produit à sauvegarder
     */
    void saveProduit(Produit produit);

}
