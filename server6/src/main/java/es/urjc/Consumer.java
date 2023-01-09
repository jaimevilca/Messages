package es.urjc;

import javax.enterprise.context.ApplicationScoped;

import es.urjc.model.EoloPlant;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
public class Consumer {

    @Incoming("eoloplantCreationProgressNotifications")
    public void process(EoloPlant eoloPlant) throws InterruptedException {
        //ToDo send client using websockets
        System.out.println("received"+eoloPlant.getCity());
    }
}