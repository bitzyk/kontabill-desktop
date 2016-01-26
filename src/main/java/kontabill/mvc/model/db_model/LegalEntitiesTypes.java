package main.java.kontabill.mvc.model.db_model;

import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.db_model.model_filters.*;
import main.java.kontabill.mvc.model.entities.*;
import main.java.kontabill.mvc.model.entities.LegalEntityType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean addLegalEntity(LegalEntity legalEntity)
    {
        boolean added = false;

        try {
            // start transaction
            getConnection().setAutoCommit(false);
            // add legal entity
            legalEntities.insertLegalEntity(((LegalEntity) legalEntity));

            // add detail
            legalEntitiesDetail.insertLegalEntityDetail(legalEntity);

            // add specific legalEntityType with id of legalEntity in the specific column
            insertLegalEntityType(legalEntity);

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
        boolean edited = false;

        try {
            // start transaction
            getConnection().setAutoCommit(false);

            // edit legal entity (name)
            legalEntities.editLegalEntity(delegat);

            // edit legal entity details
            legalEntitiesDetail.editLegalEntityDetail(delegat);

            // end transaction
            getConnection().commit();

            edited = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return edited;
    }

    public int insertLegalEntityType(LegalEntity legalEntity) throws SQLException
    {
        // prepare statement and insert
        String insertPrepared = "INSERT INTO " + TABLE_NAME +
                "(ID_CLIENT, ID_DELEGAT, ID_REPRESENTATIVE) " +
                "VALUES(?, ?, ?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(insertPrepared, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setNull(1, Types.INTEGER); //@todo insert for client
        preparedStatement.setNull(2, Types.INTEGER);
        preparedStatement.setNull(3, Types.INTEGER);

        if (legalEntity instanceof Delegat) {
            preparedStatement.setInt(2, legalEntity.getId());
        } else if(legalEntity instanceof Representative) {
            preparedStatement.setInt(3, legalEntity.getId());
        }

        return preparedStatement.executeUpdate();
    }

    private ResultSet getLegalEntities(LegalEntitiesTypesFilter legalEntitiesTypesFilter) throws SQLException
    {
        Statement sta = getConnection().createStatement();

        String sql = "SELECT lt.*, le.*, led.*, lt.ID as LEGAL_ENTITY_TYPE_ID, le.ID as LEGAL_ENTITY_ID, led.ID as LEGAL_ENTITY_DETAIL_ID" +
                " FROM " + TABLE_NAME + " lt";

        // check legal entity type and create sql accordingly
        if (legalEntitiesTypesFilter.getLegalEntityType() == main.java.kontabill.mvc.model.db_model.model_filters.LegalEntityType.DELEGAT) {
            sql = sql.concat(" JOIN " + LegalEntities.TABLE_NAME + " le ON lt.ID_DELEGAT = le.ID");
            sql = sql.concat(" JOIN " + LegalEntitiesDetail.TABLE_NAME + " led ON lt.ID_DELEGAT = led.ID_LEGAL_ENTITY");
            sql = sql.concat(" WHERE ID_DELEGAT IS NOT NULL");
        } else if (legalEntitiesTypesFilter.getLegalEntityType() == main.java.kontabill.mvc.model.db_model.model_filters.LegalEntityType.REPRESENTATIVE) {
            sql = sql.concat(" JOIN " + LegalEntities.TABLE_NAME + " le ON lt.ID_REPRESENTATIVE = le.ID");
            sql = sql.concat(" JOIN " + LegalEntitiesDetail.TABLE_NAME + " led ON lt.ID_REPRESENTATIVE = led.ID_LEGAL_ENTITY");
            sql = sql.concat(" WHERE ID_REPRESENTATIVE IS NOT NULL");
        }


        sql = sql.concat(" ORDER BY le.NAME");

        ResultSet resultSet = sta.executeQuery(sql);

        return resultSet;
    }

    public SubscribeableHashMap<Integer, Delegat> getDelegates(SubscribeableHashMap<Integer, Delegat> delegates) throws SQLException
    {
        LegalEntitiesTypesFilter legalEntitiesTypesFilter = new LegalEntitiesTypesFilter();
        legalEntitiesTypesFilter.setLegalEntityType(main.java.kontabill.mvc.model.db_model.model_filters.LegalEntityType.DELEGAT);

        ResultSet resultSet = getLegalEntities(legalEntitiesTypesFilter);

        hydrateLegalEntities(resultSet, delegates, legalEntitiesTypesFilter);

        return delegates;
    }

    public SubscribeableHashMap<Integer, Representative> getLegalRepresentatives(SubscribeableHashMap<Integer, Representative> representatives) throws SQLException
    {
        LegalEntitiesTypesFilter legalEntitiesTypesFilter = new LegalEntitiesTypesFilter();
        legalEntitiesTypesFilter.setLegalEntityType(main.java.kontabill.mvc.model.db_model.model_filters.LegalEntityType.REPRESENTATIVE);

        ResultSet resultSet = getLegalEntities(legalEntitiesTypesFilter);

        hydrateLegalEntities(resultSet, representatives, legalEntitiesTypesFilter);

        return representatives;
    }


    public <K, V extends LegalEntity> SubscribeableHashMap<Integer, V> hydrateLegalEntities(
            ResultSet resultSet,
            SubscribeableHashMap<Integer, V> subscribeableHashMap,
            LegalEntitiesTypesFilter legalEntitiesTypesFilter
    ) throws SQLException {
        while (resultSet.next()) {

            LegalEntity toHydrate;

            if (legalEntitiesTypesFilter.getLegalEntityType() == main.java.kontabill.mvc.model.db_model.model_filters.LegalEntityType.DELEGAT) {
                toHydrate = new Delegat();

            } else {
                toHydrate = new Representative();
            }

            V hydratedEntity = (V) hydrateLegalEntity(resultSet, toHydrate);
            subscribeableHashMap.putInMap(hydratedEntity.getId(), hydratedEntity);
        }

        return subscribeableHashMap;
    }

    private LegalEntity hydrateLegalEntity(ResultSet resultSet, LegalEntity legalEntity) throws SQLException
    {
        legalEntity.setId(resultSet.getInt("LEGAL_ENTITY_ID"));
        legalEntity.setIdentifier(resultSet.getString("IDENTIFIER"));
        legalEntity.setType(resultSet.getString("TYPE"));
        legalEntity.setName(resultSet.getString("NAME"));

        // assoc legal entityType to legalEntity
        LegalEntityType legalEntityType = hydrateLegalEntityType(resultSet, new LegalEntityType());
        legalEntityType.setId(resultSet.getInt("LEGAL_ENTITY_TYPE_ID"));
        legalEntity.setLegalEntityType(legalEntityType);

        // assoc legal entityDetail to legalEntity
        if (legalEntity.isPerson()) {
            LegalEntityDetailPerson legalEntityDetailPerson = new LegalEntityDetailPerson();
            hydrateLegalEntityDetailPerson(resultSet, legalEntityDetailPerson);
            legalEntityDetailPerson.setId(resultSet.getInt("LEGAL_ENTITY_DETAIL_ID"));
            legalEntity.setLegalEntityDetail(legalEntityDetailPerson);
        } else if (legalEntity.isCompany()) {

        }


        return legalEntity;
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
