package es.urjc.model;

import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class EoloPlantDeserializer implements Deserializer<EoloPlant> {
    @Override
    public EoloPlant deserialize(String topic, byte[] data) {
        String contentAsString = new String(data, StandardCharsets.UTF_8);
        String[] segments = contentAsString.split(",");
        String city = segments[1];
        String planning = segments[2];
        int progress = Integer.parseInt(segments[3]);
        boolean isCompleted = Boolean.parseBoolean(segments[4]);

        EoloPlant received = new EoloPlant(planning, city);
        received.setProgress(progress);
        received.setCompleted(isCompleted);
        return received;
    }
}