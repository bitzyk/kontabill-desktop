package main.java.kontabill.mvc.model.repository.entity_mock_generator;

import com.google.inject.Guice;
import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Entity;
import main.java.kontabill.mvc.model.repository.entity_mock_generator.populator.EntityPopulator;
import main.java.kontabill.mvc.model.repository.entity_mock_generator.populator.PopulatorEntityCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cbitoi on 31/03/16.
 */
public class EntityGenerator<T extends Entity> {

    private int noEntities; // number of entities

    private Class<T> tClass;

    public EntityGenerator(Class<T> tClass, int noEntities)
    {
        this.tClass = tClass;
        this.noEntities = noEntities;
    }


    public Map<Integer, Entity> generate()
    {
        Map<Integer, Entity> map = new HashMap<>();

        // create entity populator to hydrate mock entity
        EntityPopulator populator = PopulatorEntityCreator.createPopulator(tClass);

        for (int i = 0; i < noEntities; i++) {
            // create entity
            Entity entity = Guice.createInjector().getInstance(tClass);

            // populate the entity with mock values
            populator.populate(entity, i);

            // set entity in map
            map.put(i, entity);
        }


        return map;
    }


}
