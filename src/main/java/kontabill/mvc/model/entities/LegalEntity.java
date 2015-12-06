package main.java.kontabill.mvc.model.entities;

import main.java.kontabill.mvc.model.db_model.LegalEntitiesTypes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LegalEntity extends Entity {

    private int id;

    private String name;

    private String identifier;

    private String type;

    private LegalEntityType legalEntityType;

    private LegalEntityDetail legalEntityDetail;

    public static final String TYPE_PF = "PF";

    public static final String TYPE_PJ = "PJ";



    /**
     * Containts all possible references of legal entity types.
     * Default it not contains any reference.
     * When a get is triggered agains this variable an sql statement will be executed.
     * At least one object will be populated in the list (the current object ensure us that there is at least one row)
     *  - a legal entity could now exist if a reference to legal entity type does not exist
     */
    private List<LegalEntityType> legalEntityTypeAllReferences = new ArrayList<>();

    public String getName() {
        return name;
    }

    public LegalEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public LegalEntity setType(String type) {
        this.type = type;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LegalEntityType getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(LegalEntityType legalEntityType) {
        this.legalEntityType = legalEntityType;
    }

    public LegalEntityDetail getLegalEntityDetail() {
        return legalEntityDetail;
    }

    public void setLegalEntityDetail(LegalEntityDetail legalEntityDetail) {
        this.legalEntityDetail = legalEntityDetail;
    }

    public List<LegalEntityType> getLegalEntityTypeAllReferences()
    {
        // lazy loading query - at least one row (the current type reference for this object) should exist in the list
        if(legalEntityTypeAllReferences.size() == 0) {
            LegalEntitiesTypes legalEntitiesTypesDbTable = new LegalEntitiesTypes();
            List<LegalEntityType> legalEntityTypeAllReferences = legalEntitiesTypesDbTable.getLegalEntityTypeAllReferences(this);

            this.legalEntityTypeAllReferences = legalEntityTypeAllReferences;
        }

        return this.legalEntityTypeAllReferences;
    }

    /**
     * At least one object will be populated in the this.legalEntityTypeAllReferences
     * (the current object ensure us that there is at least one row)
     *  - a legal entity could be client, delegat etc, if at least 2 rows exist this means that has multiple entity references.
     */
    public Boolean hasMultipleEntityTypeReferences()
    {
        List<LegalEntityType> legalEntityTypes = getLegalEntityTypeAllReferences();

        Boolean hasMultipleReference = false;

        if (getLegalEntityTypeAllReferences().size() >= 2) {
            hasMultipleReference = true;
        }

        return hasMultipleReference;
    }

}
