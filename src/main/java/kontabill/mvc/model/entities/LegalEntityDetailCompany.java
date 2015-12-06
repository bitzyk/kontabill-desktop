package main.java.kontabill.mvc.model.entities;

/**
 *
 */
public class LegalEntityDetailCompany extends LegalEntityDetail {

    private String regTrade;

    public String getRegTrade() {
        return regTrade;
    }

    public LegalEntityDetailCompany setRegTrade(String regTrade) {
        this.regTrade = regTrade;
        return this;
    }
}
