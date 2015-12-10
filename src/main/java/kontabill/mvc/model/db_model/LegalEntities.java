package main.java.kontabill.mvc.model.db_model;

import main.java.kontabill.mvc.model.entities.LegalEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class LegalEntities extends DbTableAbstract {

    public final static String TABLE_NAME = "LEGAL_ENTITIES";

    public LegalEntities() {
        super(TABLE_NAME);
    }

    public int insertLegalEntity(LegalEntity legalEntity) throws SQLException
    {
        // prepare statement and insert
        String insertPrepared = "INSERT INTO " + TABLE_NAME +
                "(NAME, IDENTIFIER, TYPE) " +
                "VALUES(?, ?, ?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(insertPrepared, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, legalEntity.getName());
        preparedStatement.setString(2, legalEntity.getIdentifier());
        preparedStatement.setString(3, legalEntity.getType());

        preparedStatement.executeUpdate();

        // get insertedId keey
        ResultSet rs = preparedStatement.getGeneratedKeys();

        int insertedId = 0;

        if (rs.next()){
            insertedId = rs.getInt(1);
            legalEntity.setId(insertedId); // hydrate entity with insertedId for for insert in reference tables
        }

        return insertedId;
    }

    public int editLegalEntity(LegalEntity legalEntity) throws SQLException
    {
        // prepare statement and insert
        String prepared = "UPDATE " + TABLE_NAME +
                " SET NAME = ?" +
                " WHERE ID = ?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(prepared);
        preparedStatement.setString(1, legalEntity.getName());
        preparedStatement.setInt(2, legalEntity.getId());

        int edited = preparedStatement.executeUpdate();

        return edited;
    }


}
