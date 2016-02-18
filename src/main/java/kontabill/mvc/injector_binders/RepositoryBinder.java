package main.java.kontabill.mvc.injector_binders;


import com.google.inject.AbstractModule;
import com.google.inject.Module;
import main.java.kontabill.mvc.controller.ITest;
import main.java.kontabill.mvc.controller.Test;
import main.java.kontabill.mvc.model.repository.concrete.LegalEntitiesDatabaseRepository;
import main.java.kontabill.mvc.model.repository.interfaces.LegalEntitiesRepository;

/**
 * Created by cbitoi on 16/02/16.
 */
public class RepositoryBinder extends AbstractModule {

    @Override
    protected void configure() {
        bind(LegalEntitiesRepository.class).to(LegalEntitiesDatabaseRepository.class);
    }


}
