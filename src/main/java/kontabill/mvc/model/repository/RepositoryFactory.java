package main.java.kontabill.mvc.model.repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import main.java.kontabill.mvc.injector_binders.RepositoryBinder;
import main.java.kontabill.mvc.model.repository.interfaces.LegalEntitiesRepository;

/**
 * Created by cbitoi on 17/02/16.
 */
public abstract class RepositoryFactory {

    public static LegalEntitiesRepository getLegalEntitiesRepositoryInstance()
    {
        LegalEntitiesRepository instance = Guice.createInjector(new RepositoryBinder())
                .getInstance(LegalEntitiesRepository.class);

        return instance;
    }


}
