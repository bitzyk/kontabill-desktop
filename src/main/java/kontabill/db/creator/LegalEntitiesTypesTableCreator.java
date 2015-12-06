package main.java.kontabill.db.creator;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class LegalEntitiesTypesTableCreator extends TableCreatorAbstract {

    public static void main(String args[])
    {
        LegalEntitiesTypesTableCreator tableCreator = new LegalEntitiesTypesTableCreator();

        try {
            //tableCreator.dropTable();
            tableCreator.createTable();
            //tableCreator.addTestData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        Statement sta = db.getConnection().createStatement();

        String sql = "CREATE TABLE LEGAL_ENTITIES_TYPES" +
                "(" +
                "ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "ID_CLIENT INT," +
                "ID_DELEGAT INT," +
                "ID_REPRESENTATIVE INT," +
                "CONSTRAINT LEGAL_ENTITIES_TYPES_ID_CL FOREIGN KEY(ID_CLIENT) REFERENCES LEGAL_ENTITIES(ID)," +
                "CONSTRAINT LEGAL_ENTITIES_TYPES_ID_DL FOREIGN KEY(ID_DELEGAT) REFERENCES LEGAL_ENTITIES(ID)," +
                "CONSTRAINT LEGAL_ENTITIES_TYPES_ID_RL FOREIGN KEY(ID_REPRESENTATIVE) REFERENCES LEGAL_ENTITIES(ID)" +
                ")";

        sta.execute(sql);

        System.out.println("table created");
    }


    public void addTestData() throws SQLException {
        Statement sta = db.getConnection().createStatement();

        for (int i = 1; i <= 5; i++) {
            String sql = "INSERT INTO LEGAL_ENTITIES_TYPES(ID_DELEGAT) VALUES(" + i + ")";
            sta.executeUpdate(sql);
        }

        String sql = "INSERT INTO LEGAL_ENTITIES_TYPES(ID_CLIENT) VALUES(" + 4 + ")";
        sta.executeUpdate(sql);

        System.out.println("test data inserted");
    }

    @Override
    public void dropTable() throws SQLException {
        Statement sta = db.getConnection().createStatement();
        sta.execute("DROP TABLE LEGAL_ENTITIES_TYPES");

        System.out.println("table droped");
    }
}
