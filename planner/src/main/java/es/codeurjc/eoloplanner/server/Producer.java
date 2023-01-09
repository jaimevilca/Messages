package es.codeurjc.eoloplanner.server;

import es.codeurjc.eoloplanner.server.model.EoloPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(EoloPlant request) {
		kafkaTemplate.send("eoloplantCreationProgressNotifications", request);
	}
}
