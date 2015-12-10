package main.java.kontabill.mvc.model.db_model;

import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.LegalEntityDetail;
import main.java.kontabill.mvc.model.entities.LegalEntityDetailCompany;
import main.java.kontabill.mvc.model.entities.LegalEntityDetailPerson;

import java.sql.*;

/**
 *
 */
public class LegalEntitiesDetail extends DbTableAbstract {

    public final static String TABLE_NAME = "LEGAL_ENTITIES_DETAIL";

    public LegalEntitiesDetail() {
        super(TABLE_NAME);
    }

    public int insertLegalEntityDetail(LegalEntity legalEntity) throws SQLException
    {
        // prepare statement and insert
        String insertPrepared = "INSERT INTO " + TABLE_NAME +
                "(ID_LEGAL_ENTITY, ID_NO, ID_SERIAL, REG_TRADE) " +
                "VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(insertPrepared, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, legalEntity.getId());

        LegalEntityDetail legalEntityDetail = legalEntity.getLegalEntityDetail();

        if (legalEntityDetail instanceof LegalEntityDetailPerson) {
            preparedStatement.setString(2, ((LegalEntityDetailPerson) legalEntityDetail).getIdNo());
            preparedStatement.setString(3, ((LegalEntityDetailPerson) legalEntityDetail).getIdSerial());

            preparedStatement.setNull(4, Types.VARCHAR);
        } else if (legalEntityDetail instanceof LegalEntityDetailCompany) {
            preparedStatement.setString(4, ((LegalEntityDetailCompany) legalEntityDetail).getRegTrade());

            preparedStatement.setNull(2, Types.VARCHAR);
            preparedStatement.setNull(3, Types.VARCHAR);
        }

        preparedStatement.executeUpdate();

        // get insertedId key
        ResultSet rs = preparedStatement.getGeneratedKeys();

        int insertedId = 0;

        if (rs.next()){
            insertedId = rs.getInt(1);
        }

        return insertedId;
    }



    public int editLegalEntityDetail(LegalEntity legalEntity) throws SQLException
    {
        // prepare statement and insert
        String prepared = "UPDATE " + TABLE_NAME +
                " SET ID_NO = ?, ID_SERIAL = ?, REG_TRADE = ?" +
                " WHERE ID = ?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(prepared);

        LegalEntityDetail legalEntityDetail = legalEntity.getLegalEntityDetail();

        if (legalEntityDetail instanceof LegalEntityDetailPerson) {
            preparedStatement.setString(1, ((LegalEntityDetailPerson) legalEntityDetail).getIdNo());
            preparedStatement.setString(2, ((LegalEntityDetailPerson) legalEntityDetail).getIdSerial());

            preparedStatement.setNull(3, Types.VARCHAR);
        } else if (legalEntityDetail instanceof LegalEntityDetailCompany) {
            preparedStatement.setString(3, ((LegalEntityDetailCompany) legalEntityDetail).getRegTrade());

            preparedStatement.setNull(1, Types.VARCHAR);
            preparedStatement.setNull(2, Types.VARCHAR);
        }

        preparedStatement.setInt(4, legalEntityDetail.getId());

        int edited = preparedStatement.executeUpdate();

        return edited;
    }

}
