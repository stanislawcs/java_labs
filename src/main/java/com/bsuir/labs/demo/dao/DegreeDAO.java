package com.bsuir.labs.demo.dao;


import com.bsuir.labs.demo.models.Degree;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DegreeDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public DegreeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Degree degree) {
        Session session = sessionFactory.getCurrentSession();
        session.save(degree);
    }

}

