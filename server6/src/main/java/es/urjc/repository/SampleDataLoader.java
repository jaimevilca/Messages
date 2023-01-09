package es.urjc.repository;

import es.urjc.model.EoloPlant;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class SampleDataLoader {

    @Inject
    EoloPlantRepository posts;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        posts.persist(new EoloPlant("Madrid", "madrid-sunny-flat"));
    }
}
