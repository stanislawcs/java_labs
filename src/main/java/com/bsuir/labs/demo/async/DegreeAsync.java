package com.bsuir.labs.demo.async;

import com.bsuir.labs.demo.calculations.DegreeCalculation;
import com.bsuir.labs.demo.models.Degree;
import com.bsuir.labs.demo.service.DegreeService;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class DegreeAsync {
    private final DegreeService degreeService;
    private final DegreeCalculation degreeCalculation;

    public DegreeAsync(DegreeService degreeService, DegreeCalculation degreeCalculation) {
        this.degreeService = degreeService;
        this.degreeCalculation = degreeCalculation;
    }

    public Integer createAsync(Degree degree) {

        Degree degree1 = new Degree();
        degree1.setDegrees(degree.getDegrees());
        degreeService.save(degree1);

        return degree1.getId();
    }

    public CompletableFuture<Integer> computeAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Degree result = degreeService.findOne(id);

                Thread.sleep(15000);
                result.setRadians(degreeCalculation.calculate(result.getDegrees()));
                degreeService.save(result);

                return result.getId();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });

    }

}
