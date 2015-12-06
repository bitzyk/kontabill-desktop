package main.java.kontabill.mvc.model.db_model;

import main.java.kontabill.Kontabill;
import main.java.kontabill.db.Database;

import java.sql.Connection;

/**
 *
 */
abstract public class DbTableAbstract {

    private String tableName;

    private Database database;

    private Connection connection;

    public DbTableAbstract(String tableName) {
        this.tableName = tableName;

        database = Kontabill.getInstance().getDb();
        connection = database.getConnection();
    }

    public String getTableName() {
        return tableName;
    }

    public Database getDatabase() {
        return database;
    }

    public Connection getConnection() {
        return connection;
    }
}
