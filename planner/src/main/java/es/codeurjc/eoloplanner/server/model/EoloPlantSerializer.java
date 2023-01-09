package es.codeurjc.eoloplanner.server.model;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class EoloPlantSerializer implements Serializer<EoloPlant> {
    public byte[] serialize(String topic, EoloPlant data) {
        return (
                data.getId() + "," + data.getCity() + "," + data.getPlanning() + "," + data.getProgress() + ","
                + data.isCompleted()).getBytes(StandardCharsets.UTF_8);
    }
}