package es.urjc;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import es.urjc.model.EoloPlant;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class Producer {

    @Channel("eoloplantCreationRequests")
    Emitter<EoloPlant> messages;

    private int numData;

    @Transactional
    void send(EoloPlant eoloPlant){
        messages.send(eoloPlant);
    }
}