package main.java.kontabill.db.creator;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cbitoi on 23/11/15.
 */
public class LegalEntitiesDetailTableCreator extends TableCreatorAbstract {

    public static void main(String args[])
    {
        LegalEntitiesDetailTableCreator tableCreator = new LegalEntitiesDetailTableCreator();

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
        sta.execute("DROP TABLE LEGAL_ENTITIES_DETAIL");

        System.out.println("table droped");
    }

    public void createTable() throws SQLException {
        Statement sta = db.getConnection().createStatement();

        sta.execute("SET SCHEMA KONTABILL");


        String sql = "CREATE TABLE LEGAL_ENTITIES_DETAIL" +
                "(" +
                "ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "ID_LEGAL_ENTITY INT,"  +
                "ID_NO VARCHAR(20)," +
                "ID_SERIAL VARCHAR(20)," +
                "REG_TRADE VARCHAR(40)," +
                "CONSTRAINT LEGAL_ENTITIES_DETAIL_ID_LEGAL_ENTITY FOREIGN KEY(ID_LEGAL_ENTITY) REFERENCES LEGAL_ENTITIES(ID)" +
                ")";

        sta.execute(sql);

        System.out.println("table created");
    }


    public void addTestData() throws SQLException {
        Statement sta = db.getConnection().createStatement();

        for (int i = 1; i <= 1; i++) {
            String sql = "INSERT INTO LEGAL_ENTITIES_DETAIL(ID_LEGAL_ENTITY, ID_NO, ID_SERIAL)" +
                    " VALUES(" + 2 +
                    ", '123456" + i + "'" +
                    ", 'RR')";
            sta.executeUpdate(sql);
        }

        System.out.println("test data inserted");
    }
}
