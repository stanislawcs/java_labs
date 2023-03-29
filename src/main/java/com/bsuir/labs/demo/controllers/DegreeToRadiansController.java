package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.async.DegreeAsync;
import com.bsuir.labs.demo.cache.Cache;
import com.bsuir.labs.demo.calculations.DegreeCalculation;
import com.bsuir.labs.demo.counter.Counter;
import com.bsuir.labs.demo.counter.CounterThread;
import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import com.bsuir.labs.demo.models.Degree;
import com.bsuir.labs.demo.service.DegreeService;
import com.bsuir.labs.demo.validation.DegreeValidation;
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
    private final DegreeService degreeService;
    private final DegreeCalculation degreeCalculation;
    private final DegreeValidation degreeValidation;
    private final DegreeAsync degreeAsync;


    private static final Logger logger = LogManager.getLogger(DegreeToRadiansController.class);

    @Autowired
    public DegreeToRadiansController(Cache<Double, Double> cache,
                                     DegreeService degreeService,
                                     DegreeCalculation degreeCalculation,
                                     DegreeValidation degreeValidation, DegreeAsync degreeAsync) {

        this.cache = cache;
        this.degreeService = degreeService;
        this.degreeCalculation = degreeCalculation;
        this.degreeValidation = degreeValidation;
        this.degreeAsync = degreeAsync;
    }

    @GetMapping("/degree")
    public ResponseEntity<?> degreesToRadians(@RequestParam("degree") double degree,
                                              @ModelAttribute("degrees") Degree degrees)
            throws IllegalArgumentsException {

        CounterThread counterThread = new CounterThread();
        counterThread.start();

        logger.info("GetMapping by address localhost:8080/degree?degree=...");

        degreeValidation.validate(degree);
        logger.info("Degree validation (if degree>360||degree<-360) - Exception");

        double result;
        if (!cache.contain(degree)) {
            logger.info("calculate");
            result = degreeCalculation.calculate(degree);
            logger.info("Push to cache");
            cache.push(degree, result);
        } else {
            logger.info("get from cache");
            result = cache.get(degree);
        }

        degrees.setDegrees(degree);
        degrees.setRadians(result);
        //degreeService.save(degrees);

        return new ResponseEntity<>("result " + Counter.getCounter() + ":" + result, HttpStatus.OK);
    }


    @PostMapping("/degree")
    public ResponseEntity<?> listOfDegreeToRadiansController(@RequestBody List<Double> listOfDegree) {

        List<Double> responseList = listOfDegree.stream().map(degreeCalculation::calculate).collect(Collectors.toList());

        double sum = degreeCalculation.findSum(responseList);
        double min = degreeCalculation.findMin(responseList);
        double max = degreeCalculation.findMax(responseList);


        return new ResponseEntity<>("Result: " + responseList + "\n" + "min: " + min + "\n"
                + "max: " + max + "\nsum: " + sum, HttpStatus.OK);
    }

    @PostMapping("/async")
    public Integer method(@RequestBody Degree degree) throws IllegalArgumentsException {
        degreeValidation.validate(degree.getDegrees());
        int id = degreeAsync.createAsync(degree);

        degreeAsync.computeAsync(id);
        return id;
    }

    @GetMapping("/result/{id}")
    public Degree result(@PathVariable("id") int id){
        return degreeService.findOne(id);
    }





}
