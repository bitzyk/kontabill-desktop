package main.java.kontabill.db.creator;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cbitoi on 23/11/15.
 */
public class LegalEntitiesTableCreator extends TableCreatorAbstract {

    public static void main(String args[])
    {
        LegalEntitiesTableCreator tableCreator = new LegalEntitiesTableCreator();

        try {
            //tableCreator.dropTable();
            tableCreator.createTable();
            //tableCreator.addTestData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() throws SQLException {
        Statement sta = db.getConnection().createStatement();
        sta.execute("DROP TABLE LEGAL_ENTITIES");

        System.out.println("table droped");
    }

    public void createTable() throws SQLException {
        Statement sta = db.getConnection().createStatement();

        sta.execute("SET SCHEMA KONTABILL");



        String sql = "CREATE TABLE LEGAL_ENTITIES" +
                "(" +
                "ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "NAME VARCHAR(255) NOT NULL," +
                "IDENTIFIER VARCHAR(100)," +
                "TYPE VARCHAR(5) NOT NULL CONSTRAINT LEGAL_ENTITIES_TYPE_ck CHECK(TYPE IN ('PF', 'PJ'))" +
                ")";

        sta.execute(sql);

        sta.execute("CREATE INDEX LEGAL_ENTITIES_NAME_INDEX ON LEGAL_ENTITIES(NAME)");
        sta.execute("CREATE INDEX LEGAL_ENTITIES_IDENTIFIER_INDEX ON LEGAL_ENTITIES(IDENTIFIER)");

        System.out.println("table created");
    }


    public void addTestData() throws SQLException {
        Statement sta = db.getConnection().createStatement();

        for (int i = 1; i <= 5; i++) {
            String sql = "INSERT INTO LEGAL_ENTITIES(NAME, IDENTIFIER, TYPE)" +
                    " VALUES('Cristian Bitoi" + i + "'" +
                    ", '1861225410019" + i + "'" +
                    ", 'PF')";
            sta.executeUpdate(sql);
        }

        System.out.println("test data inserted");
    }
}
