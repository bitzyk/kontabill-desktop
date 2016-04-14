package main.java.kontabill.mvc.model.repository.entity_mock_generator.populator;

import main.java.kontabill.mvc.model.entities.Entity;

/**
 * Created by cbitoi on 01/04/16.
 */
public interface EntityPopulator {

    public <T extends Entity> void populate(T toPopulate, int id);
}
