package main.java.kontabill.db.creator;

import main.java.kontabill.db.Database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cbitoi on 02/12/15.
 */
public class SchemaCreator {


    Database db = Database.getInstance();

    public static void main(String args[])
    {
        SchemaCreator schemaCreator = new SchemaCreator();

        try {
            schemaCreator.createSchema();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSchema() throws SQLException {
        Statement sta = db.getConnection().createStatement();
        sta.execute("CREATE SCHEMA KONTABILL AUTHORIZATION kontabill");

        System.out.println("schema created");

    }
}
