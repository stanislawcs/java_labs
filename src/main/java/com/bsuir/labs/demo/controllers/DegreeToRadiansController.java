package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.cache.Cache;
import com.bsuir.labs.demo.counter.Counter;
import com.bsuir.labs.demo.counter.CounterThread;
import com.bsuir.labs.demo.dao.DegreeDAO;
import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import com.bsuir.labs.demo.models.Degree;
import com.bsuir.labs.demo.service.DegreeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class DegreeToRadiansController {

    private final Cache<Double, Double> cache;
    private final CounterThread counterThread;
    private final DegreeService degreeService;
    private final Degree degrees;
    private final DegreeDAO degreeDAO;

    private static final Logger logger = LogManager.getLogger(DegreeToRadiansController.class);

    @Autowired
    public DegreeToRadiansController(Cache<Double, Double> cache, CounterThread counterThread,
                                     DegreeService degreeService, Degree degrees, DegreeDAO degreeDAO) {
        this.cache = cache;
        this.counterThread = counterThread;
        this.degreeService = degreeService;
        this.degrees = degrees;
        this.degreeDAO = degreeDAO;
    }

    @GetMapping("/degree")
    public ResponseEntity<?> degreesToRadians(@RequestParam("degree") double degree) throws IllegalArgumentsException {


        counterThread.run();
        logger.info("GetMapping by address localhost:8080/degree?degree=...");

        degreeService.validate(degree);
        logger.info("Degree validation (if degree>360||degree<-360) - Exception");

        double result;
        if (!cache.contain(degree)) {
            logger.info("calculate");
            result = degreeService.calculate(degree);
            logger.info("Push to cache");
            cache.push(degree, result);
        } else {
            logger.info("get from cache");
            result = cache.get(degree);
        }

        degrees.setDegrees(degree);
        degrees.setRadians(result);
       // degreeDAO.save(degrees);

        return new ResponseEntity<>("result " + Counter.getCounter() + ":" + result, HttpStatus.OK);
    }


    @PostMapping("/degree")
    public ResponseEntity<?> listOfDegreeToRadiansController(@RequestBody List<Double> listOfDegree) {

        List<Double> responseList = listOfDegree.stream().map(degreeService::calculate).collect(Collectors.toList());

        double sum = degreeService.findSum(responseList);
        double min = degreeService.findMin(responseList);
        double max = degreeService.findMax(responseList);



        return new ResponseEntity<>("Result: " + responseList + "\n" + "min: " + min + "\n"
                + "max: " + max + "\nsum: " + sum, HttpStatus.OK);
    }


}
