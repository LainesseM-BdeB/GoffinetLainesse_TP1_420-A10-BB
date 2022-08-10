package dao;

import java.sql.Connection;
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

}
