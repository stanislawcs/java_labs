package com.bsuir.labs.demo.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "degree_service")
@Component
public class Degree {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "degrees")
    private double degrees;

    @Column(name = "radians")
    private double radians;

    public Degree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }

    public String toString() {
        return "degrees: " + degrees + ", radians: " + radians;
    }


}
