package main.java.kontabill.mvc.model.repository.concrete;

import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.Representative;
import main.java.kontabill.mvc.model.repository.interfaces.LegalEntitiesRepository;

import java.util.HashMap;
import java.util.List;
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
    public boolean add(Delegat delegat) {
        return true;
    }

    @Override
    public boolean add(Representative representative) {
        return true;
    }

    @Override
    public boolean add(Client client) {
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
    public boolean remove(Client client) {
        return false;
    }

    @Override
    public boolean remove(LegalEntity legalEntity) {
        return false;
    }

    @Override
    public int removeAll(List<Client> clients) {
        return 0;
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
