package main.java.kontabill.mvc.controller;

import main.java.kontabill.Kontabill;
import main.java.kontabill.lib.core.request.Request;

/**
 * - creeaza modelul pt controller-ul curent
 *
 */
public abstract class BaseAbstractController {


    /**
     * Represent kontabill instance app.
     * Can be used to build layout components according with kontabill instance object state.
     */
    private Kontabill kontabill;

    private Request request;

    /**
     * creeaza model=ul default
     * primeste kontabillapp
     */
    public BaseAbstractController(Kontabill kontabill)
    {
        this.kontabill = kontabill;
        setModelOfController();
    }


    protected abstract void setModelOfController();


    public Kontabill getKontabill() {
        return kontabill;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getControllerName()
    {
        return this.getClass().getSimpleName();
    }
}
