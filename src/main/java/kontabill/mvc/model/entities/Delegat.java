package main.java.kontabill.mvc.model.entities;

/**
 *
 */
public class Delegat extends LegalEntity {

    @Override
    public LegalEntityDetailPerson getLegalEntityDetail() {
        return (LegalEntityDetailPerson) super.getLegalEntityDetail();
    }
}
