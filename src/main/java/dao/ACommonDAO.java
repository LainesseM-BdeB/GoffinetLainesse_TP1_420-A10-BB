package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class ACommonDAO<T> {

    protected Connection connection;

    public ACommonDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract T create(T object);

    public abstract boolean delete(T object);

    public abstract boolean update(T object);

    public abstract T findByID(int id);

    public abstract T findByName(String name);

    public abstract T findByValues(double value1, double value2);

    public abstract ArrayList<T> findAll();

    /**
     * Obtention de la liste des objets de la base de données
     * @param resultSet
     * @return liste des objets obtenus de la base de données
     */public abstract ArrayList<T> getListOfResults(ResultSet resultSet);

    /**
     * Affichage dans la console de la liste des objets
     * @param liste des objets
     */
    public abstract void afficherListe(ArrayList<Object> liste);

}
