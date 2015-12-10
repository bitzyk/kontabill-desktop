package main.java.kontabill.mvc.model.catalog;

import main.java.kontabill.mvc.model.BaseAbstractModel;
import main.java.kontabill.mvc.model.db_model.LegalEntitiesTypes;
import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.entities.Delegat;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class CatalogModel extends BaseAbstractModel {

    private LegalEntitiesTypes legalEntitiesTypeDbTable = new LegalEntitiesTypes();

    public SubscribeableHashMap<String, Delegat> getDelegates()
    {
        SubscribeableHashMap<String, Delegat> subscribeableHashMap = new SubscribeableHashMap<>();

        try {
            subscribeableHashMap = legalEntitiesTypeDbTable.getDelegates(subscribeableHashMap);
        } catch (SQLException e) {
            System.out.println("-- Error: sql exception --");
            System.exit(1);
        }

        return subscribeableHashMap;
    }

    public SubscribeableHashMap<String, Delegat> getDelegatesThread()
    {
        SubscribeableHashMap<String, Delegat> subscribeableHashMap = new SubscribeableHashMap<>();


        class GetDelegatesRunable implements Runnable {

            SubscribeableHashMap<String, Delegat> subscribeableHashMap;

            public GetDelegatesRunable(SubscribeableHashMap<String, Delegat> subscribeableHashMap) {
                this.subscribeableHashMap = subscribeableHashMap;
            }

            @Override
            public void run()
            {
                try {
                    legalEntitiesTypeDbTable.getDelegates(this.subscribeableHashMap);
                    subscribeableHashMap.setThreadFinished(true);
                } catch (SQLException e) {
                    System.out.println("-- Error: sql exception --");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        Thread thread = new Thread(new GetDelegatesRunable(subscribeableHashMap));
        thread.start();

        return subscribeableHashMap;
    }

    public int deleteDelegatesAction(ArrayList<Delegat> delegats)
    {
        int delegatsDeleted = 0;

        for (int i = 0; i < delegats.size(); i++) {
            Delegat delegat = delegats.get(i);
            Boolean deleted = legalEntitiesTypeDbTable.deleteDelegat(delegat);

            if(deleted) {
                delegatsDeleted += 1;
            }
        }

        return delegatsDeleted;
    }

    public boolean addDelegat(Delegat delegat)
    {
        boolean added = false;

        added = legalEntitiesTypeDbTable.addDelegat(delegat);

        return added;
    }

    public boolean editDelegat(Delegat delegat)
    {
        boolean edited = false;

        edited = legalEntitiesTypeDbTable.editDelegat(delegat);

        return edited;
    }


}
