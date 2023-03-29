package com.bsuir.labs.demo.service;

import com.bsuir.labs.demo.models.Degree;
import com.bsuir.labs.demo.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DegreeService {

    private final DegreeRepository degreeRepository;

    @Autowired
    public DegreeService(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Transactional
    public void save(Degree degree) {
        degreeRepository.save(degree);
    }

    @Transactional
    public Degree findOne(int id){
        Optional<Degree> foundDegree = degreeRepository.findById(id);
        return foundDegree.orElse(null);
    }


}

