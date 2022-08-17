package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Ceci est la classe abstraite pour nos DAO"
 * @author Maxime Lainesse et Gaëlle Goffinet
 * @since 2022/08/03
 */
public abstract class ACommonDAO<T> {

    protected Connection connection;

    public ACommonDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Supression d'un objet dans la base de données
     * @param object
     * @return true si l'objet est supprimé
     */
    public abstract boolean delete(T object);

    /**
     * Mise à jour de l'objet
     * @param object
     * @return true si l'objet a été mis à jour
     */
    public abstract boolean update(T object);

    /**
     * Recherche un élément dans la table selon sa clé primaire
     * @param id
     * @return l'élément s'il existe dans la table
     */
    public abstract T findByID(int id);

    /**
     * Obtention de la liste de tous les éléments d'une table
     * @return liste des éléments de la table
     */
    public abstract ArrayList findAll();

    /**
     * Obtention de la liste des objets de la base de données
     * @param resultSet
     * @return liste des objets obtenus de la base de données
     */
    protected abstract ArrayList<T> getListOfResults(ResultSet resultSet);

    /**
     * Affichage dans la console de la liste des objets avec leurs différents paramètres
     * @param liste des objets
     */
    protected abstract void afficherListe(ArrayList<Object> liste);

}
