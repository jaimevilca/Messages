package es.codeurjc.eoloplanner.server;

import es.codeurjc.eoloplanner.server.model.EoloPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(EoloPlant request) {
        simulateProcessWaiting();
        request.addProgress();
        System.out.println("Message: " + request.toString() + " topic :" + "eoloplantCreationProgressNotifications");
        kafkaTemplate.send("eoloplantCreationProgressNotifications", request);
    }

    private void simulateProcessWaiting() {
        try {
            Thread.sleep(1000 + new Random().nextInt(2000));
        } catch (InterruptedException e) {
        }
    }


}
