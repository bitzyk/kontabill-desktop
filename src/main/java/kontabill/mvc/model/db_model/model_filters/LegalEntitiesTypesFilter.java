package main.java.kontabill.mvc.model.db_model.model_filters;


/**
 *
 */
public class LegalEntitiesTypesFilter implements ModelFilter {

    private LegalEntityType legalEntityType;

    public LegalEntityType getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(LegalEntityType legalEntityType) {
        this.legalEntityType = legalEntityType;
    }
}