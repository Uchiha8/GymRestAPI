package com.epam.repository;

import com.epam.domain.TrainingType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingTypeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public TrainingTypeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TrainingType save(TrainingType trainingType) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(trainingType);
            session.getTransaction().commit();
            return trainingType;
        }
    }

    public List<TrainingType> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select t from TrainingType t", TrainingType.class).getResultList();
        }
    }

    public TrainingType findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select t from TrainingType t where t.name = :name", TrainingType.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
    }

    public boolean existsByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(t) > 0 from TrainingType t where t.name = :name", Boolean.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
    }
}
