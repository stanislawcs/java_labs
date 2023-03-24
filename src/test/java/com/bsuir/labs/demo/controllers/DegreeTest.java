package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import com.bsuir.labs.demo.service.DegreeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DegreeTest {

    private final double EPS = 1e-2;

    @Test
    public void validationShouldThrowException() {
        DegreeService degreeService = new DegreeService();
        Assertions.assertThrows(IllegalArgumentsException.class, () -> {
            degreeService.validate(381);
        });
    }

    @Test
    public void resultOfCalculationShouldBeZero(){
        DegreeService degreeService = new DegreeService();
        Assertions.assertEquals(0,degreeService.calculate(0),EPS);
    }

    @Test
    public void resultOfRightBoundOfDegreeInterval(){
        DegreeService degreeService = new DegreeService();
        Assertions.assertEquals(6.28,degreeService.calculate(360),EPS);
    }

    @Test
    public void resultOfLeftBoundOfDegreeInterval() {
        DegreeService degreeService = new DegreeService();
        Assertions.assertEquals(0, degreeService.calculate(-360), EPS);
    }

}
