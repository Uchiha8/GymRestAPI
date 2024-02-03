package com.epam.repository;

import com.epam.domain.Trainee;
import com.epam.domain.Trainer;
import com.epam.domain.Training;
import com.epam.domain.TrainingType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public TrainingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Training save(Training training) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(training);
            session.getTransaction().commit();
            return training;
        }
    }

    public boolean existsByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(t) > 0 from Training t where t.trainingName = :name", Boolean.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
    }
}
