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
        StringBuffer planningCreation = new StringBuffer(city);

        CompletableFuture<Void> landscape = topoClient.getLandscape(city).thenAccept(l -> {
            System.out.println("L");
            planningCreation.append("-");
            planningCreation.append(l);
            producer.sendMessage(setCalculationPercentage(request, request.getProgress() + 25));
        });

        CompletableFuture<Void> weather = weatherClient.getWeather(city).thenAccept(w -> {
            System.out.println("W");
            planningCreation.append("-");
            planningCreation.append(w);
            producer.sendMessage(setCalculationPercentage(request, request.getProgress() + 25));
        });

        producer.sendMessage(setCalculationPercentage(request, request.getProgress() + 25));

        CompletableFuture.allOf(weather, landscape).get();

        String planning = planningCreation.toString();

        planning = planning.matches("^[A-Ma-m].*") ?
                planning.toLowerCase() :
                planning.toUpperCase();

        simulateProcessWaiting();
        producer.sendMessage(setCalculationPercentage(request, 100));
        return new EoloPlant(request.getCity(), planning);
    }

    private void simulateProcessWaiting() {
        try {
            Thread.sleep(1000 + new Random().nextInt(2000));
        } catch (InterruptedException e) {}
    }

    private EoloPlant setCalculationPercentage(EoloPlant eoloPlant, int percentaje){
        eoloPlant.setProgress(percentaje);
        eoloPlant.setCompleted(percentaje == 100);
        return eoloPlant;
    }
}
