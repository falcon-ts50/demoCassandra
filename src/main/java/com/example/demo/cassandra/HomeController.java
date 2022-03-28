package com.example.demo.cassandra;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HomeController {

    @Autowired
    IngredientRepository ir;

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/")
    public String home(){
        return "<center><h1>I am in home</h1></center>";
    }

    @GetMapping("/create/ingredient")
    public void create(){
        Ingredient ingredient = ir.save(new Ingredient(Uuids.timeBased(), "Pesto", Ingredient.Type.CHEESE));
        System.out.println(ingredient);
    }

    @GetMapping("/createsensor")
    public void createSensor(){
        System.out.println("start creating sensors");
        LocalDate today = LocalDate.now();
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger count = new AtomicInteger(1);
        Callable<String> task = () -> {
            for (int a = 0; a < 24; a ++) {
                int id = count.getAndIncrement();
                LocalTime time = LocalTime.of(0,0, 0);
                String sensorName = "" + (Math.random() + a +" "+a*Math.random()).hashCode();
                Instant start = Instant.now();
                for (int i = 0; i < 86400 ; i++) {
                    Sensor sensor = new Sensor(id,today.toString(), time.toString(), sensorName, "Voltage of Line 380V", 1, 1, 755, "We", true, (float) (Math.random()*10 + 375));
                    sensorRepository.save(sensor);
                    time = time.plusSeconds(1);
                }
                Instant end = Instant.now();
                System.out.println("Running time for " + sensorName +  " is: " + Duration.between(start, end).get(ChronoUnit.SECONDS) + " s");
                CompletableFuture.runAsync(() -> System.out.println("count of sensors: " + id));
            }
            return "1";
        };

        for (int i = 0; i < 8; i++) {
            executorService.submit(task);

        }
        executorService.shutdown();
    }

    @GetMapping("/getsensors/{id}")
    public ResponseEntity<String> getSensor(@PathVariable(name = "id") int id){
        Instant start = Instant.now();
//        ArrayList<Sensor> sensors = new ArrayList<>();
//        sensors.addAll(sensorRepository.findBySensorId(id));
//        sensors.addAll(sensorRepository.findBySensorId(2));
//        sensors.addAll(sensorRepository.findBySensorId(3));
//        sensors.addAll(sensorRepository.findBySensorId(4));
//        sensors.addAll(sensorRepository.findBySensorId(5));
//        7 секунд
        Iterable<Sensor> sensors = sensorRepository.findBySensorId(id);
        Iterable<Sensor> sensors2 = sensorRepository.findBySensorId(2);
        Iterable<Sensor> sensors3 = sensorRepository.findBySensorId(3);
        Iterable<Sensor> sensors4 = sensorRepository.findBySensorId(4);
        Iterable<Sensor> sensors5 = sensorRepository.findBySensorId(5);
//        5,6 секунды

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Iterable<Sensor> sensors6;
//        Iterable<Sensor> sensors7;
//        Iterable<Sensor> sensors8;
//        Iterable<Sensor> sensors9;
//        Iterable<Sensor> sensors10;
//
//        Callable<String> task = () -> {
//            return "1";
//        };
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(task);
//        }

        Instant stop = Instant.now();
        Duration duration = Duration.between(start, stop);
        String string = "Duration is: "+ duration.toMillis() + " s" ;
        return new ResponseEntity<>(string, HttpStatus.OK);
    }

    private void avgFunction(LocalDateTime fromDate, LocalDateTime toDate, int points, String... sensor){
        int per = (int) (Duration.between(toDate, fromDate).toMillis()/points);
    }
}
