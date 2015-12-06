package main.java.kontabill.db.creator;

import main.java.kontabill.db.Database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public abstract class TableCreatorAbstract {

    Database db = Database.getInstance();

    public TableCreatorAbstract() {

        try {
            Statement sta = db.getConnection().createStatement();
            sta.execute("SET SCHEMA KONTABILL");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public abstract void createTable() throws SQLException;

    public abstract void addTestData() throws SQLException;

    public abstract void dropTable() throws SQLException;

}
