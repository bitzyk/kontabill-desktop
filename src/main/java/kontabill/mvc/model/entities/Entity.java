package main.java.kontabill.mvc.model.entities;

/**
 * Base class for entities classses
 */
public abstract class Entity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
