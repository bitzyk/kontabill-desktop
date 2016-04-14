package main.java.kontabill.mvc.model.repository.entity_mock_generator.populator;

import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Entity;
import main.java.kontabill.mvc.model.entities.LegalEntity;

/**
 * Created by cbitoi on 01/04/16.
 */
public class ClientPopulator implements EntityPopulator {

    private Client toPopulate;

    @Override
    public <T extends Entity> void populate(T toPopulate, int id) {
        this.toPopulate = (Client) toPopulate;

        this.toPopulate.setId(id);

        if(id % 2 == 0) {
            this.toPopulate.setType(LegalEntity.TYPE_PF);
            this.toPopulate.setName("Cristian_" + id);
            this.toPopulate.setIdentifier("1861225410019");
        } else {
            this.toPopulate.setType(LegalEntity.TYPE_PJ);
            this.toPopulate.setName("PFA Bitoi Cristian_" + id);
            this.toPopulate.setIdentifier("2254100");
        }
    }
}
