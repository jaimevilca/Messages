package es.codeurjc.eoloplanner.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.eoloplanner.server.model.EoloPlant;
import es.codeurjc.eoloplanner.server.service.EoloPlantCreatorService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class Consumer {

	@Autowired
	private EoloPlantCreatorService eoloPlantCreator;

	@KafkaListener(topics = "eoloplantCreationRequests")
	public void received(ConsumerRecord<?, ?> message) throws ExecutionException, InterruptedException, JsonProcessingException {
		System.out.println("Message: "+message.value());
		EoloPlant eoloPlantInput = getEoloPlant((String) message.value());
		eoloPlantCreator.createEoloPlant(eoloPlantInput);
	}

	private EoloPlant getEoloPlant(String value) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(value, EoloPlant.class);
	}
}
