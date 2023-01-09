package es.urjc.repository;

import es.urjc.model.EoloPlant;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EoloPlantRepository implements PanacheRepository<EoloPlant> {
}
