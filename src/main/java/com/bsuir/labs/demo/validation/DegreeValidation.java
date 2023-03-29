package com.bsuir.labs.demo.validation;

import com.bsuir.labs.demo.exceptions.IllegalArgumentsException;
import org.springframework.stereotype.Component;

@Component
public class DegreeValidation {

    public void validate(double degree) throws IllegalArgumentsException {
        if (degree > 360 || degree < -360) {
            throw new IllegalArgumentsException("Illegal arguments");
        }
    }


}
