package es.urjc;

import es.urjc.dto.EoloPlantResponseDto;
import es.urjc.model.EoloPlant;
import es.urjc.repository.EoloPlantRepository;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Id;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

@GraphQLApi
public class EoloPlantResource {

    @Inject
    EoloPlantRepository eoloPlants;

    @Inject
    Producer producer;


    @Query
    public Collection<EoloPlant> eoloPlants() {
        return List.of(new EoloPlant());
    }

    @Query
    public EoloPlant eoloPlant(@Id long id) {
        return new EoloPlant();
    }

    @Mutation
    @ReactiveTransactional
    public Uni<EoloPlantResponseDto> createEoloPlant(EoloPlant eoloPlant) {
        eoloPlant.setProgress(0);
        eoloPlant.setCompleted(false);
        return eoloPlants.persist(eoloPlant).onItem()
                .transform(p -> {
                    producer.send(p);
                    return new EoloPlantResponseDto(p.getId(), p.getCity(), p.getPlanning());
                });
    }
    /*
    @Query
    public Collection<EoloPlant> eoloPlants() {
        return eoloPlants.listAll();
    }

    @Query
    public EoloPlant eoloPlant(@Id long id) {
        return eoloPlants.findByIdOptional(id).orElseThrow();
    }


        * */
/*
}

    @Mutation
    @Transactional
    public EoloPlant replaceEoloPlant(@Id long id, EoloPlant eoloPlant) {
        eoloPlants.findByIdOptional(id).orElseThrow();
        eoloPlant.setId(id);
        eoloPlants.getEntityManager().merge(eoloPlant);
        return eoloPlant;
    }

    @Mutation
    @Transactional
    public EoloPlant deleteEoloPlant(@Id long id) {
        EoloPlant eoloPlant = eoloPlants.findByIdOptional(id).orElseThrow();
        eoloPlants.deleteById(id);
        return eoloPlant;
    }
    */
}