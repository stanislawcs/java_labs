package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import com.bsuir.labs.demo.models.Degree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DegreeTest {
    @Test
    void validateException(){
        Assertions.assertThrows(IllegalArgumentsException.class,()->{
            Degree.validate(390);
        }) ;
    }



}
