package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.calculations.DegreeCalculation;
import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import com.bsuir.labs.demo.validation.DegreeValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DegreeTest {

    private final double EPS = 1e-2;

    @Test
    public void validationShouldThrowException() {
        DegreeValidation degreeValidation = new DegreeValidation();
        Assertions.assertThrows(IllegalArgumentsException.class, () -> {
            degreeValidation.validate(381);
        });
    }

    @Test
    public void resultOfCalculationShouldBeZero() {
        DegreeCalculation degreeCalculation = new DegreeCalculation();
        Assertions.assertEquals(0, degreeCalculation.calculate(0), EPS);
    }

    @Test
    public void resultOfRightBoundOfDegreeInterval() {
        DegreeCalculation degreeCalculation = new DegreeCalculation();
        Assertions.assertEquals(6.28, degreeCalculation.calculate(360), EPS);
    }

    @Test
    public void resultOfLeftBoundOfDegreeInterval() {
        DegreeCalculation degreeCalculation = new DegreeCalculation();
        Assertions.assertEquals(0, degreeCalculation.calculate(-360), EPS);
    }

}
