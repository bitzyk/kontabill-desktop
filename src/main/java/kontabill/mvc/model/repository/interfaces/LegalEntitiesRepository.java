package main.java.kontabill.mvc.model.repository.interfaces;

import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.Representative;

import java.util.List;
import java.util.Map;

/**
 * Created by cbitoi on 16/02/16.
 */
public interface LegalEntitiesRepository extends Repository {

    public Client getClientById(int id);

    public Delegat getDelegatById(int id);

    public Delegat getRepresentativeById(int id);

    public Map<Integer, Delegat> getAllDelegates();

    public Map<Integer, Representative> getAllLegalRepresentatives();

    public Map<Integer, Client> getAllClients();

    public boolean add(Delegat delegat);

    public boolean add(Representative representative);

    public boolean add(Client client);

    public boolean add(LegalEntity legalEntity);

    public boolean remove(Delegat delegat);

    public boolean remove(Representative representative);

    public boolean remove(Client client);

    public boolean remove(LegalEntity legalEntity);

    public int removeAll(List<Client> clients);

}
