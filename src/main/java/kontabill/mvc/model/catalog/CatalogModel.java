package main.java.kontabill.mvc.model.catalog;

import main.java.kontabill.mvc.model.BaseAbstractModel;
import main.java.kontabill.mvc.model.db_model.LegalEntitiesTypes;
import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.Representative;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class CatalogModel extends BaseAbstractModel {

    private LegalEntitiesTypes legalEntitiesTypeDbTable = new LegalEntitiesTypes();

    public SubscribeableHashMap<Integer, Delegat> getDelegates()
    {
        SubscribeableHashMap<Integer, Delegat> subscribeableHashMap = new SubscribeableHashMap<>();

        try {
            subscribeableHashMap = legalEntitiesTypeDbTable.getDelegates(subscribeableHashMap);
        } catch (SQLException e) {
            System.out.println("-- Error: sql exception --");
            System.exit(1);
        }

        return subscribeableHashMap;
    }

    public SubscribeableHashMap<Integer, Representative> getLegalRepresentatives()
    {
        SubscribeableHashMap<Integer, Representative> subscribeableHashMap = new SubscribeableHashMap<>();

        try {
            subscribeableHashMap = legalEntitiesTypeDbTable.getLegalRepresentatives(subscribeableHashMap);
        } catch (SQLException e) {
            System.out.println("-- Error: sql exception --");
            System.exit(1);
        }

        return subscribeableHashMap;
    }

    public SubscribeableHashMap<Integer, Delegat> getDelegatesThread()
    {
        SubscribeableHashMap<Integer, Delegat> subscribeableHashMap = new SubscribeableHashMap<>();


        class GetDelegatesRunable implements Runnable {

            SubscribeableHashMap<Integer, Delegat> subscribeableHashMap;

            public GetDelegatesRunable(SubscribeableHashMap<Integer, Delegat> subscribeableHashMap) {
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

    public SubscribeableHashMap<Integer, Representative> getRepresentativeThread()
    {
        SubscribeableHashMap<Integer, Representative> subscribeableHashMap = new SubscribeableHashMap<>();


        class GetRepresentativeRunable implements Runnable {

            SubscribeableHashMap<Integer, Representative> subscribeableHashMap;

            public GetRepresentativeRunable(SubscribeableHashMap<Integer, Representative> subscribeableHashMap) {
                this.subscribeableHashMap = subscribeableHashMap;
            }

            @Override
            public void run()
            {
                try {
                    legalEntitiesTypeDbTable.getLegalRepresentatives(this.subscribeableHashMap);
                    subscribeableHashMap.setThreadFinished(true);
                } catch (SQLException e) {
                    System.out.println("-- Error: sql exception --");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        Thread thread = new Thread(new GetRepresentativeRunable(subscribeableHashMap));
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

    public int deleteRepresentativessAction(ArrayList<Representative> representatives)
    {
        int representativesDeleted = 0;

        for (int i = 0; i < representatives.size(); i++) {
            Representative representative = representatives.get(i);
            Boolean deleted = legalEntitiesTypeDbTable.deleteRepresentative(representative);

            if(deleted) {
                representativesDeleted += 1;
            }
        }

        return representativesDeleted;
    }

    public boolean addDelegat(Delegat delegat)
    {
        boolean added = false;

        added = legalEntitiesTypeDbTable.addLegalEntity(delegat);

        return added;
    }

    public boolean addLegalRepresentative(Representative entity)
    {
        boolean added = false;

        added = legalEntitiesTypeDbTable.addLegalEntity(entity);

        return added;
    }


    public boolean editDelegat(Delegat delegat)
    {
        boolean edited = false;

        edited = legalEntitiesTypeDbTable.editDelegat(delegat);

        return edited;
    }


}
