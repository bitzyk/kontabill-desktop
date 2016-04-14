package main.java.kontabill.mvc.model.repository.concrete;

import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.Representative;
import main.java.kontabill.mvc.model.repository.entity_mock_generator.EntityGenerator;
import main.java.kontabill.mvc.model.repository.interfaces.LegalEntitiesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cbitoi on 16/02/16.
 */
public class LegalEntitiesInMemoryRepository implements LegalEntitiesRepository {

    private Map<Integer, Client> clients = new HashMap<>();

    @Override
    public Client getClientById(int id) {
        return null;
    }

    @Override
    public Delegat getDelegatById(int id) {
        return null;
    }

    @Override
    public Delegat getRepresentativeById(int id) {
        return null;
    }

    @Override
    public Map<Integer, Delegat> getAllDelegates() {
        return null;
    }

    @Override
    public Map<Integer, Representative> getAllLegalRepresentatives() {
        return null;
    }

    @Override
    public Map<Integer, Client> getAllClients() {

        // if no clients in memory then generate mock clients
        if(clients.size() == 0) {
            EntityGenerator generator = new EntityGenerator<Client>(Client.class, 10);
            clients = generator.generate();
        }

        return clients;
    }

    @Override
    public boolean add(Delegat delegat) {
        return true;
    }

    @Override
    public boolean add(Representative representative) {
        return true;
    }

    @Override
    public boolean add(Client client) {

        // generate a mock id
        int generatedId = 500 + (int)(Math.random() * (1000 - 500)); // between [500 -1000)

        // add client to memory
        clients.put(generatedId, client);

        // set id to entity (this usually happens after db insert, so this is the reason for positioning after clients.put())
        client.setId(generatedId);

        return true;
    }

    @Override
    public boolean add(LegalEntity legalEntity) {
        return true;
    }

    @Override
    public boolean remove(Delegat delegat) {
        return false;
    }

    @Override
    public boolean remove(Representative representative) {
        return false;
    }

    @Override
    public boolean remove(Client client)
    {
        // remove client from memory
        Object result = clients.remove(client.getId());

        if(result != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(LegalEntity legalEntity) {
        return false;
    }

    @Override
    public int removeAll(List<Client> clients)
    {
        int deleted = 0;
        for (Client client : clients) {
            if ( remove(client) ) {
                deleted += 1;
            }
        }

        return deleted;
    }

    @Override
    public LegalEntity findById(Object id) {
        return null;
    }

    @Override
    public Object getAll() {
        return null;
    }
}
