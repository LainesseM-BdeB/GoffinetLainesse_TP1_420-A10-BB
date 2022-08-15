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

    public abstract boolean delete(T object);

    public abstract boolean update(T object);

    public abstract T findByID(int id);

    public abstract ArrayList findAll();

    /**
     * Obtention de la liste des objets de la base de données
     * @param resultSet
     * @return liste des objets obtenus de la base de données
     */public abstract ArrayList<T> getListOfResults(ResultSet resultSet);

    /**
     * Affichage dans la console de la liste des objets avec leurs différents paramètres
     * @param liste des objets
     */
    public abstract void afficherListe(ArrayList<Object> liste);

}
