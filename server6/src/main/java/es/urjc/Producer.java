package es.urjc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import es.urjc.model.EoloPlant;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class Producer {

    private final static String TOPIC = "eoloplantCreationRequests";
    @Channel(TOPIC)
    Emitter<EoloPlant> messages;

    @Inject
    WebsocketHandler webHandler;
    private int numData;

    void send(EoloPlant eoloPlant) {
        //TODO registrar la session con el id de la planta eolica
        System.out.println("Message: " + eoloPlant.toString() + " topic :" + TOPIC);
        messages.send(eoloPlant);
    }
}