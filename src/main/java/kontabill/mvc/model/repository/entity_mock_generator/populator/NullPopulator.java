package main.java.kontabill.mvc.model.repository.entity_mock_generator.populator;

import main.java.kontabill.mvc.model.entities.Entity;

/**
 * Created by cbitoi on 01/04/16.
 */
public class NullPopulator implements EntityPopulator {

    @Override
    public <T extends Entity> void populate(T toPopulate, int id) {
        // don't do anything
        // null implementation
    }
}
