package main.java.kontabill.mvc.controller;

import com.google.inject.Guice;
import main.java.kontabill.Kontabill;
import main.java.kontabill.lib.core.request.RequestSessionKey;
import main.java.kontabill.mvc.model.catalog.CatalogModel;
import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.core.SubscribeableHashMapListener;
import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.Representative;
import main.java.kontabill.mvc.model.forms.ClientForm;
import main.java.kontabill.mvc.model.forms.DelegatForm;
import main.java.kontabill.mvc.model.forms.RepresentativeForm;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.model.repository.RepositoryFactory;
import main.java.kontabill.mvc.model.repository.interfaces.LegalEntitiesRepository;
import main.java.kontabill.mvc.view.catalog.CatalogClientsView;
import main.java.kontabill.mvc.view.catalog.CatalogDelegatesView;
import main.java.kontabill.mvc.view.catalog.CatalogLegalRepresentativesView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// delete
//import org.apache.derby.jdbc.EmbeddedDriver;
// delete

/**
 *
 *
 */
public class CatalogController extends BaseAbstractController {

    protected CatalogModel model;

    protected LegalEntitiesRepository legalEntitiesRepository;


    public CatalogController(Kontabill kontabill) {
        super(kontabill);

        legalEntitiesRepository = RepositoryFactory.getLegalEntitiesRepositoryInstance();
    }

    public void catalogClientsAction()
    {
        SubscribeableHashMap<Integer, Client> clientSubscribeableHashMap = new SubscribeableHashMap<>();

        // query the clients in a thread (non blocking)
        new Thread(() -> {
            SwingUtilities.invokeLater(() -> {
                Map<Integer, Client> clients = legalEntitiesRepository.getAllClients();

                clientSubscribeableHashMap.putAll(clients);
                clientSubscribeableHashMap.setThreadFinished(true);
            });
        }).start();

        CatalogClientsView catalogClientsView = new CatalogClientsView(this, clientSubscribeableHashMap);
        catalogClientsView.render();

    }

    public void catalogDelegatesAction()
    {
        SubscribeableHashMap delegatesHashMap = model.getDelegatesThread();

        CatalogDelegatesView catalogDelegatesView = new CatalogDelegatesView(this, delegatesHashMap);
        catalogDelegatesView.render();
    }

    public void catalogLegalRepresentativesAction()
    {
        SubscribeableHashMap representativeHashMap = model.getRepresentativeThread();

        CatalogLegalRepresentativesView catalogLegalRepresentativesView
                = new CatalogLegalRepresentativesView(this, representativeHashMap);

        catalogLegalRepresentativesView.render();
    }


    public void deleteDelegatesAction(ArrayList<Delegat> delegats)
    {
        // delete delegates
        model.deleteDelegatesAction(delegats);

        // redirect back to catalogDelegatesAction
        getKontabill().getMVC().runController("catalogDelegatesAction", getRequest());
    }


    public void deleteClientsAction(ArrayList<Client> clients)
    {
        // delete clients
        int deleted = legalEntitiesRepository.removeAll(clients);

        System.out.println(
                "deleted: " + deleted
        );

        // redirect
        getKontabill().getMVC().runController("catalogClientsAction", getRequest());
    }

    public void deleteLegalRepresentativesAction(ArrayList<Representative> representatives)
    {
        // delete representatives
        model.deleteRepresentativessAction(representatives);

        // redirect back to catalogDelegatesAction
        getKontabill().getMVC().runController(
                "catalogLegalRepresentativesAction",
                getRequest()
        );
    }

    public void addDelegatAction(BaseAbstractForm form)
    {
        if(form.validate()) {
            // deal with the form
            Delegat delegatEntity = new Delegat();
            ((DelegatForm)form).hydrateEntity(delegatEntity);

            model.addDelegat(delegatEntity);

            // if delegat has been edited set addedId in session
            if(delegatEntity.getId() > 0) {
                getRequest().getSessionPayload().addDataItem(RequestSessionKey.ADDED_KEY, delegatEntity.getId());
            }

            // redirect
            getKontabill().getMVC().runController("catalogDelegatesAction", getRequest());
        }
    }

    public void addClientAction(BaseAbstractForm form)
    {
        if(form.validate()) {
            // deal with the form
            Client client = Guice.createInjector().getInstance(Client.class);

            ((ClientForm)form).hydrateEntity(client);

            legalEntitiesRepository.add(client);

            // if entity has been edited set addedId in session
            if(client.getId() > 0) {
                System.out.println(
                        "adddedId: "+ client.getId()
                );
                getRequest().getSessionPayload().addDataItem(RequestSessionKey.ADDED_KEY, client.getId());
            }

            // redirect
            getKontabill().getMVC().runController("catalogClientsAction", getRequest());
        }
    }

    public void addLegalRepresentativeAction(BaseAbstractForm form)
    {
        if(form.validate()) {
            // deal with the form
            Representative entity = new Representative();
            ((RepresentativeForm)form).hydrateEntity(entity);

            model.addLegalRepresentative(entity);

            // if delegat has been edited set addedId in session
            if(entity.getId() > 0) {
                getRequest().getSessionPayload().addDataItem(RequestSessionKey.ADDED_KEY, entity.getId());
            }

            // redirect
            getKontabill().getMVC().runController("catalogLegalRepresentativesAction", getRequest());
        }
    }

    public void editClientAction(Client clientEntity, BaseAbstractForm form)
    {
        System.out.println(
                "-- editeza client"
        );

//        if (form.validate()) {
//            // rewrite entity with values from form (except the id)
//            ((DelegatForm)form).hydrateEntity(clientEntity);
//
//            boolean edited = model.editDelegat(clientEntity);
//
//            // if delegat has been edited set editedId in session
//            if(edited == true) {
//                getRequest().getSessionPayload().addDataItem(RequestSessionKey.EDITED_KEY, clientEntity.getId());
//            }
//
//            // redirect back to catalogDelegatesAction
//            getKontabill().getMVC().runController("catalogDelegatesAction", getRequest());
//        }
    }

    public void editDelegatAction(Delegat delegatEntity, BaseAbstractForm form)
    {
        if (form.validate()) {
            // rewrite entity with values from form (except the id)
            ((DelegatForm)form).hydrateEntity(delegatEntity);

            boolean edited = model.editDelegat(delegatEntity);

            // if delegat has been edited set editedId in session
            if(edited == true) {
                getRequest().getSessionPayload().addDataItem(RequestSessionKey.EDITED_KEY, delegatEntity.getId());
            }

            // redirect back to catalogDelegatesAction
            getKontabill().getMVC().runController("catalogDelegatesAction", getRequest());
        }
    }

    public void editRepresentativeAction(Representative representativeEntity, BaseAbstractForm form)
    {
        if (form.validate()) {
            // rewrite entity with values from form (except the id)
            ((RepresentativeForm)form).hydrateEntity(representativeEntity);

            boolean edited = model.editRepresentative(representativeEntity);

            // if representative has been edited set editedId in session
            if(edited == true) {
                getRequest().getSessionPayload().addDataItem(RequestSessionKey.EDITED_KEY, representativeEntity.getId());
            }

            // redirect back to catalogLegalRepresentativesAction
            getKontabill().getMVC().runController("catalogLegalRepresentativesAction", getRequest());
        }
    }


    @Override
    protected void setModelOfController() {
        model = new CatalogModel();
    }
}
