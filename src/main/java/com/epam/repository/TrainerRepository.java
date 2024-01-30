package com.epam.repository;

import com.epam.domain.Trainee;
import com.epam.domain.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public TrainerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Trainer save(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(trainer);
            session.getTransaction().commit();
            return trainer;
        }
    }
}
