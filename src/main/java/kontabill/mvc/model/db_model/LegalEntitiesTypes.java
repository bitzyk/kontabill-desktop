package main.java.kontabill.mvc.model.db_model;

import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 */
public class LegalEntitiesTypes extends DbTableAbstract {

    public final static String TABLE_NAME = "LEGAL_ENTITIES_TYPES";

    private LegalEntities legalEntities = new LegalEntities();

    private LegalEntitiesDetail legalEntitiesDetail = new LegalEntitiesDetail();

    public LegalEntitiesTypes() {
        super(TABLE_NAME);
    }

    public boolean addDelegat(Delegat delegat)
    {
        boolean added = false;

        try {
            // start transaction
            getConnection().setAutoCommit(false);
            // add legal entity
            legalEntities.insertLegalEntity(((LegalEntity) delegat));

            // add detail
            legalEntitiesDetail.insertLegalEntityDetail(delegat);

            // add specific legalEntityType with id of legalEntity in the specific column
            insertLegalEntityType(delegat);

            // end transaction
            getConnection().commit();
            getConnection().setAutoCommit(true);

            added = true; // set added flag to true
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return added;
    }

    public boolean editDelegat(Delegat delegat)
    {
        boolean added = false;

        try {
            // start transaction
            getConnection().setAutoCommit(false);

            // edit legal entity (name)
            legalEntities.editLegalEntity(delegat);

            // edit legal entity details
            legalEntitiesDetail.editLegalEntityDetail(delegat);

            // end transaction
            getConnection().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return added;
    }

    public int insertLegalEntityType(LegalEntity legalEntity) throws SQLException
    {
        // prepare statement and insert
        String insertPrepared = "INSERT INTO " + TABLE_NAME +
                "(ID_CLIENT, ID_DELEGAT, ID_REPRESENTATIVE) " +
                "VALUES(?, ?, ?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(insertPrepared, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setNull(1, Types.INTEGER); //@todo insert for client
        if (legalEntity instanceof Delegat) {
            preparedStatement.setInt(2, legalEntity.getId());
        }
        preparedStatement.setNull(3, Types.INTEGER); //@todo insert for representative

        return preparedStatement.executeUpdate();
    }

    public SubscribeableHashMap<String, Delegat> getDelegates(SubscribeableHashMap<String, Delegat> delegates) throws SQLException {

        Statement sta = getConnection().createStatement();

        String sql = "SELECT lt.*, le.*, led.*, lt.ID as LEGAL_ENTITY_TYPE_ID, le.ID as LEGAL_ENTITY_ID, led.ID as LEGAL_ENTITY_DETAIL_ID" +
                " FROM " + TABLE_NAME + " lt" +
                " JOIN " + LegalEntities.TABLE_NAME + " le ON lt.ID_DELEGAT = le.ID" +
                " JOIN " + LegalEntitiesDetail.TABLE_NAME + " led ON lt.ID_DELEGAT = led.ID_LEGAL_ENTITY" +
                " WHERE ID_DELEGAT IS NOT NULL";

        ResultSet resultSet = sta.executeQuery(sql);

        hydrateDelegates(resultSet, delegates);

        return delegates;
    }


    public SubscribeableHashMap<String, Delegat> hydrateDelegates(
            ResultSet resultSet,
            SubscribeableHashMap<String, Delegat> delegates
    ) throws SQLException {
        while (resultSet.next()) {
            Delegat delegat = hydrateDelegat(resultSet, new Delegat());

            delegates.put(delegat.getId(), delegat);
        }

        return delegates;
    }

    private Delegat hydrateDelegat(ResultSet resultSet, Delegat delegat) throws SQLException
    {
        delegat.setId(resultSet.getInt("LEGAL_ENTITY_ID"));
        delegat.setIdentifier(resultSet.getString("IDENTIFIER"));
        delegat.setType(resultSet.getString("TYPE"));
        delegat.setName(resultSet.getString("NAME"));

        // assoc legal entityType to legalEntity
        LegalEntityType legalEntityType = hydrateLegalEntityType(resultSet, new LegalEntityType());
        legalEntityType.setId(resultSet.getInt("LEGAL_ENTITY_TYPE_ID"));
        delegat.setLegalEntityType(legalEntityType);

        // assoc legal entityDetail to legalEntity
        if (delegat.isPerson()) {
            LegalEntityDetailPerson legalEntityDetailPerson = new LegalEntityDetailPerson();
            hydrateLegalEntityDetailPerson(resultSet, legalEntityDetailPerson);
            legalEntityDetailPerson.setId(resultSet.getInt("LEGAL_ENTITY_DETAIL_ID"));
            delegat.setLegalEntityDetail(legalEntityDetailPerson);
        } else if (delegat.isCompany()) {

        }


        return delegat;
    }

    private LegalEntityType hydrateLegalEntityType(ResultSet resultSet, LegalEntityType legalEntityType) throws SQLException
    {
        legalEntityType.setId(resultSet.getInt("LEGAL_ENTITY_TYPE_ID"));

        return legalEntityType;
    }

    private void hydrateLegalEntityDetailPerson(ResultSet resultSet, LegalEntityDetailPerson legalEntityDetailPerson) throws SQLException
    {
        legalEntityDetailPerson
                .setIdSerial(resultSet.getString("ID_SERIAL"))
                .setIdNo(resultSet.getString("ID_NO"));
    }


    public Boolean deleteDelegat(Delegat delegat)
    {
        Boolean deleted = null;
        try {
            deleted = deleteLegalEntity(delegat);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    public Boolean deleteClient()
    {
        // return deleteLegalEntity(client);
        return true;
    }

    /**
     * Delete legat entity from database
     * @return
     */
    public Boolean deleteLegalEntity(LegalEntity legalEntity) throws SQLException {

        Boolean hasMultipleEntityTypeReferences = legalEntity.hasMultipleEntityTypeReferences();

        Statement sta = getConnection().createStatement();

        // delete legal entity type
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID=" + legalEntity.getLegalEntityType().getId();
        int noDeleted = sta.executeUpdate(sql);

        // if this legal entity does not have multiple references delete also the legal entity
        if (hasMultipleEntityTypeReferences == false) {
            // delete first details of legal entity
            sql = "DELETE FROM " + LegalEntitiesDetail.TABLE_NAME + " WHERE ID_LEGAL_ENTITY=" + legalEntity.getId();
            sta.executeUpdate(sql);

            sql = "DELETE FROM " + LegalEntities.TABLE_NAME + " WHERE ID=" + legalEntity.getId();
            sta.executeUpdate(sql);
        }

        Boolean deleted = false;
        if(noDeleted > 0) {
            deleted = true;
        }

        return deleted;
    }

    public List<LegalEntityType> getLegalEntityTypeAllReferences(LegalEntity legalEntity)
    {
       List<LegalEntityType> legalEntityTypes =  new ArrayList<>();

        Statement sta = null;
        try {
            sta = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "SELECT lt.*, lt.ID as LEGAL_ENTITY_TYPE_ID" +
                " FROM " + TABLE_NAME + " lt " +
                " WHERE ID_DELEGAT=" + legalEntity.getId() +
                " OR ID_CLIENT=" + legalEntity.getId() +
                " OR ID_REPRESENTATIVE=" + legalEntity.getId();

        try {
            ResultSet resultSet = sta.executeQuery(sql);

            while (resultSet.next()) {
                LegalEntityType legalEntityType = hydrateLegalEntityType(resultSet, new LegalEntityType());
                legalEntityTypes.add(legalEntityType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return legalEntityTypes;
    }

}
