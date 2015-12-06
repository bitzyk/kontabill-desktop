package main.java.kontabill.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class Database {

    private static Database instance;

    private static String dbUrl = "jdbc:derby:db/kontabill;user=kontabill;create=true";

    private Connection connection;

    private Database()
    {
        try {
            System.out.println(" -- database get connectionn --");
             connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Database getInstance()
    {
        if (! (instance instanceof Database) ) {
            instance = new Database();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
