package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class ACommonDAO<T> {

    protected Connection connection;

    public ACommonDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract boolean delete(T object);

    /**
     * Mise à jour de l'objet "Client"
     * @param object article
     * @return true si l'objet a été mis à jour
     */
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
