package main.java.kontabill.mvc.model.entities;

/**
 *
 */
public class Representative extends LegalEntity {

    @Override
    public LegalEntityDetailPerson getLegalEntityDetail() {
        return (LegalEntityDetailPerson) super.getLegalEntityDetail();
    }
}
