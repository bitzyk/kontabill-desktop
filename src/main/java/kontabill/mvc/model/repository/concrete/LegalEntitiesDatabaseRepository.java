package main.java.kontabill.mvc.model.repository.concrete;

import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.Representative;
import main.java.kontabill.mvc.model.repository.interfaces.LegalEntitiesRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cbitoi on 16/02/16.
 */
public class LegalEntitiesDatabaseRepository implements LegalEntitiesRepository {

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

        Map<Integer, Client> clientMap = new HashMap<>();

        return clientMap;
    }

    @Override
    public int add(Delegat delegat) {
        return 0;
    }

    @Override
    public int add(Representative representative) {
        return 0;
    }

    @Override
    public int add(Client client) {
        return 0;
    }

    @Override
    public int add(LegalEntity legalEntity) {
        return 0;
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
    public boolean remove(Client client) {
        return false;
    }

    @Override
    public boolean remove(LegalEntity legalEntity) {
        return false;
    }

    @Override
    public Object findById(Object id) {
        return null;
    }

    @Override
    public Object getAll() {
        return null;
    }
}
