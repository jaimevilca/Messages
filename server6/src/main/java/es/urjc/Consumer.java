package es.urjc;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;

import javax.websocket.WebSocketContainer;
import javax.ws.rs.client.Client;

import es.urjc.model.EoloPlant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.io.IOException;
import java.net.URI;


@ApplicationScoped
public class Consumer {

    @Incoming("eoloplantCreationProgressNotifications")
    public void consume(ConsumerRecord<String, EoloPlant> record) {
        EoloPlant eoloPlant = record.value();
        System.out.println("Message: " + eoloPlant.toString() + " topic :" + record.topic());


        //TODO Enviar los mensajes al websocket por session

    }
}