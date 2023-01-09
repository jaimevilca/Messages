package es.urjc;

import es.urjc.model.EoloPlant;
import es.urjc.repository.EoloPlantRepository;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Id;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@GraphQLApi
public class EoloPlantResource {

    @Inject
    EoloPlantRepository eoloPlants;

    @Inject
    Producer producer;

    @Query
    public Collection<EoloPlant> eoloPlants() {
        return eoloPlants.listAll();
    }

    @Query
    public EoloPlant eoloPlant(@Id long id) {
        return eoloPlants.findByIdOptional(id).orElseThrow();
    }

    @Mutation
    @Transactional(SUPPORTS)
    public EoloPlant createEoloPlant(EoloPlant eoloPlant) {
        producer.send(eoloPlant);
        eoloPlants.persist(eoloPlant);
        return eoloPlant;
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
}