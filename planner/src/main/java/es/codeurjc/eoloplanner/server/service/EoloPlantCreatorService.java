package es.codeurjc.eoloplanner.server.service;

import es.codeurjc.eoloplanner.server.Producer;
import es.codeurjc.eoloplanner.server.client.TopoClient;
import es.codeurjc.eoloplanner.server.client.WeatherClient;
import es.codeurjc.eoloplanner.server.model.EoloPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class EoloPlantCreatorService {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private TopoClient topoClient;

    @Autowired
    private Producer producer;


    public EoloPlant createEoloPlant(EoloPlant request) throws ExecutionException, InterruptedException {

        String city = request.getCity();

        CompletableFuture<String> landscape = topoClient.getLandscape(city).thenCompose(l -> {
            producer.sendMessage(request);
            return CompletableFuture.completedFuture(l);
        });
        CompletableFuture<String> weather = weatherClient.getWeather(city).thenCompose(l -> {
            producer.sendMessage(request);
            return CompletableFuture.completedFuture(l);
        });
        producer.sendMessage(request);
        CompletableFuture.allOf(weather, landscape).join();
        addPlanning(landscape.get(),weather.get(),request);
        producer.sendMessage(request);

        return request;
    }

    private void addPlanning(String landscape, String weather, EoloPlant request) {
        StringBuffer planningCreation = new StringBuffer(request.getCity());

        planningCreation.append("-");
        planningCreation.append(landscape);
        planningCreation.append("-");
        planningCreation.append(weather);
        String planning = planningCreation.toString();
        planning = planning.matches("^[A-Ma-m].*") ?
                planning.toLowerCase() :
                planning.toUpperCase();
        request.setPlanning(planning);
    }
}
