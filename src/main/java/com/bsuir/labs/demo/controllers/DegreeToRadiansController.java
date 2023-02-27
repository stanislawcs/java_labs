package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.cache.Cache;
import com.bsuir.labs.demo.counter.Counter;
import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import com.bsuir.labs.demo.models.Degree;
import com.bsuir.labs.demo.models.Radian;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DegreeToRadiansController {

    private Cache<Degree, Radian> cache;

    private static final Logger logger = LogManager.getLogger(DegreeToRadiansController.class);

    @Autowired
    public DegreeToRadiansController(Cache<Degree, Radian> cache) {
        this.cache = cache;
    }

    @GetMapping("/degree")
    public String degreesToRadians(@RequestParam("degree") double degree) throws JSONException,
            IllegalArgumentsException {


        logger.info("GetMapping by address localhost:8080/degree?degree=...");
        Degree degrees = new Degree(degree);

        logger.info("Degree validation (if degree>360||degree<-360) - Exception");
        Degree.validate(degrees.getDegrees());

        Radian radian;

        logger.info("Using cache");

        if (!cache.contain(degrees)) {
            logger.info("calculate");
            radian = Radian.calculate(degrees.getDegrees());
        } else {
            logger.info("get from cache");
            radian = cache.get(degrees);
        }


        JSONObject response = new JSONObject();


        logger.info("Push to cache");
        cache.push(degrees, radian);


        return response.put("result: " + Counter.increment(), radian.getRadians()).toString();
    }

}
