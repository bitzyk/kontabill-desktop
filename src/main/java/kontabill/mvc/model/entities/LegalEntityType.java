package main.java.kontabill.mvc.model.entities;

/**
 *
 */
public class LegalEntityType extends Entity {

    private int id;

    private int idClient;

    private int idDelegat;

    private int idRepresentative;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdDelegat() {
        return idDelegat;
    }

    public void setIdDelegat(int idDelegat) {
        this.idDelegat = idDelegat;
    }

    public int getIdRepresentative() {
        return idRepresentative;
    }

    public void setIdRepresentative(int idRepresentative) {
        this.idRepresentative = idRepresentative;
    }
}
