package main.java.kontabill.mvc.model.repository.entity_mock_generator.populator;

import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.Entity;

/**
 * Created by cbitoi on 01/04/16.
 */
abstract public class PopulatorEntityCreator {

    public static EntityPopulator createPopulator(Class<?> tClass)
    {
        EntityPopulator populator;

        if ( tClass.getSimpleName().equals("Client") ) {
            populator = new ClientPopulator();
        } else if ( tClass.getSimpleName().equals("Delegat") ) {
            populator = new DelegatPopulator();
        } else {
            populator = new NullPopulator();
        }

        return populator;
    }

}
