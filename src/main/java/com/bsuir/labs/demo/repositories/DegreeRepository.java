package com.bsuir.labs.demo.repositories;

import com.bsuir.labs.demo.models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer> {
}
