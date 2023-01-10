package es.urjc.model;

import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class EoloPlantDeserializer implements Deserializer<EoloPlant> {
    @Override
    public EoloPlant deserialize(String topic, byte[] data) {
        String contentAsString = new String(data, StandardCharsets.UTF_8);
        String[] segments = contentAsString.split(",");
        Long id = Long.valueOf(segments[0]);
        String city = segments[1];
        String planning = segments[2];
        int progress = Integer.valueOf(segments[3]);
        boolean isCompleted = Boolean.parseBoolean(segments[4]);

        EoloPlant received = new EoloPlant(id, city, planning, progress, isCompleted);

        return received;
    }
}