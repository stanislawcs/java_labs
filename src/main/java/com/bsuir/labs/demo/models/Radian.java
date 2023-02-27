package com.bsuir.labs.demo.models;

public class Radian {
    private double radians;

    public Radian() {
    }

    public Radian(double radians) {
        this.radians = radians;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }

    public static Radian calculate(double degree) {

        if (degree < 0) {
            degree += 360;
        }

        Radian radian = new Radian();
        radian.setRadians(degree / 57.3);

        return radian;
    }

}
