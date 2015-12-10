package main.java.kontabill.mvc.controller;

import main.java.kontabill.Kontabill;
import main.java.kontabill.mvc.model.catalog.CatalogModel;
import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.forms.DelegatForm;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.view.catalog.CatalogClientsView;
import main.java.kontabill.mvc.view.catalog.CatalogDelegatesView;

import java.util.ArrayList;

// delete
//import org.apache.derby.jdbc.EmbeddedDriver;
// delete

/**
 *
 *
 */
public class CatalogController extends BaseAbstractController {

    protected CatalogModel model;


    public CatalogController(Kontabill kontabill) {
        super(kontabill);
    }

    public void catalogClientsAction()
    {
        CatalogClientsView catalogClientsView = new CatalogClientsView(this);
        catalogClientsView.render();

    }

    public void catalogDelegatesAction()
    {
        //SubscribeableHashMap delegatesHashMap = model.getDelegates();
        SubscribeableHashMap delegatesHashMap = model.getDelegatesThread();

        CatalogDelegatesView catalogDelegatesView = new CatalogDelegatesView(this, delegatesHashMap);
        catalogDelegatesView.render();
    }


    public void deleteDelegatesAction(ArrayList<Delegat> delegats)
    {
        // delete delegates
        model.deleteDelegatesAction(delegats);

        // redirect back to catalogDelegatesAction
        getKontabill().getMVC().runController("catalogDelegatesAction", getRequest());
    }

    public void addDelegatAction(BaseAbstractForm form)
    {
        if(form.validate()) {
            // deal with the form
            Delegat delegatEntity = new Delegat();
            ((DelegatForm)form).hydrateEntity(delegatEntity);

            model.addDelegat(delegatEntity);

            // redirect
            getKontabill().getMVC().runController("catalogDelegatesAction", getRequest());
        }
    }

    public void editDelegatAction(Delegat delegatEntity, BaseAbstractForm form)
    {
        if (form.validate()) {
            // rewrite entity with values from form (except the id)
            ((DelegatForm)form).hydrateEntity(delegatEntity);

            model.editDelegat(delegatEntity);

            // redirect back to catalogDelegatesAction
            getKontabill().getMVC().runController("catalogDelegatesAction", getRequest());
        }
    }


    @Override
    protected void setModelOfController() {
        model = new CatalogModel();
    }
}
