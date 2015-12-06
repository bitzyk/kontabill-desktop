package main.java.kontabill.mvc.model.db_model;

import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.LegalEntityType;

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

    public LegalEntitiesTypes() {
        super(TABLE_NAME);
    }

    public void addDelegat(Delegat delegat)
    {
        try {
            // start transaction
            getConnection().setAutoCommit(false);
            // add legal entity
            legalEntities.insertLegalEntity(((LegalEntity) delegat));

            // add detail

            // add specific legalEntityType with id of legalEntity in the specific column
            insertLegalEntityType(delegat);


            // end transaction
            getConnection().commit();
            getConnection().setAutoCommit(true);
            System.out.println("-- add delegat a luat sfarsit --");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
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

        String sql = "SELECT lt.*, le.*, lt.ID as LEGAL_ENTITY_TYPE_ID, le.ID as LEGAL_ENTITY_ID" +
                " FROM " + TABLE_NAME + " lt " +
                "JOIN " + LegalEntities.TABLE_NAME + " le ON lt.ID_DELEGAT = le.ID" +
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

        return delegat;
    }

    private LegalEntityType hydrateLegalEntityType(ResultSet resultSet, LegalEntityType legalEntityType) throws SQLException
    {
        legalEntityType.setId(resultSet.getInt("LEGAL_ENTITY_TYPE_ID"));

        return legalEntityType;
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
