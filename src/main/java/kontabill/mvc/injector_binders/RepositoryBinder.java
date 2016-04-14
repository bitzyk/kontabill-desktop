package main.java.kontabill.mvc.injector_binders;


import com.google.inject.AbstractModule;
import com.google.inject.Module;
import main.java.kontabill.mvc.model.repository.concrete.LegalEntitiesDatabaseRepository;
import main.java.kontabill.mvc.model.repository.concrete.LegalEntitiesInMemoryRepository;
import main.java.kontabill.mvc.model.repository.interfaces.LegalEntitiesRepository;

/**
 * Created by cbitoi on 16/02/16.
 */
public class RepositoryBinder extends AbstractModule {

    @Override
    protected void configure() {
        bind(LegalEntitiesRepository.class).to(LegalEntitiesInMemoryRepository.class);
    }


}
