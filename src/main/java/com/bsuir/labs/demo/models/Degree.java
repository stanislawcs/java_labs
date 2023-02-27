package com.bsuir.labs.demo.models;

import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;

import java.util.Objects;

public class Degree {
    private double degrees;

    public Degree(double degrees) {
        this.degrees = degrees;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public static void validate(double degree) throws IllegalArgumentsException, RuntimeException {

        if (degree > 360 || degree < -360) {
            throw new IllegalArgumentsException("Illegal arguments");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree = (Degree) o;
        return Double.compare(degree.degrees, degrees) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(degrees);
    }

}
