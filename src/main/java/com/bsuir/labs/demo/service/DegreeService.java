package com.bsuir.labs.demo.service;

import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DegreeService {

    public void validate(double degree) throws IllegalArgumentsException {

        if (degree > 360 || degree < -360) {
            throw new IllegalArgumentsException("Illegal arguments");
        }

    }

    public double calculate(double degree) {
        if (degree < 0) {
            degree += 360;
        }
        return degree / 57.3;
    }

    public double findSum(List<Double> listOfDegree) {
        double sum = 0;
        if (!listOfDegree.isEmpty())
            sum = listOfDegree.stream().mapToDouble(Double::doubleValue).sum();
        return sum;
    }

    public double findMin(List<Double> listOfDegree) {
        double min = 0;

        if (!listOfDegree.isEmpty()) {
            min = listOfDegree.stream().min(Double::compareTo).get();
        }
        return min;
    }

    public double findMax(List<Double> listOfDegree) {
        double max = 0;

        if (!listOfDegree.isEmpty()) {
            max = listOfDegree.stream().max(Double::compareTo).get();
        }
        return max;
    }

}

