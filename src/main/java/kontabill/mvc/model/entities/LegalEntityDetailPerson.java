package main.java.kontabill.mvc.model.entities;

/**
 *
 */
public class LegalEntityDetailPerson extends LegalEntityDetail {

    private String idNo;

    private String idSerial;

    public String getIdNo() {
        return idNo;
    }

    public LegalEntityDetailPerson setIdNo(String idNo) {
        this.idNo = idNo;
        return this;
    }

    public String getIdSerial() {
        return idSerial;
    }

    public LegalEntityDetailPerson setIdSerial(String idSerial) {
        this.idSerial = idSerial;
        return this;
    }
}
