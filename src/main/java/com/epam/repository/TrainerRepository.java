package com.epam.repository;

import com.epam.domain.Trainee;
import com.epam.domain.Trainer;
import com.epam.domain.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public boolean existsByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(t) > 0 from Trainer t where t.user.username = :username", Boolean.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }
    }

    public Trainer findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select t from Trainer t where t.user.username = :username", Trainer.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }
    }

    public Trainer update(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(trainer);
            session.getTransaction().commit();
            return trainer;
        }
    }

    public List<Trainer> activeTrainers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select t from Trainer t where t.user.active = true", Trainer.class)
                    .getResultList();
        }
    }
    public void updateStatus(String username, boolean status) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update User u set u.active = :status where u.username = :username")
                    .setParameter("status", status)
                    .setParameter("username", username)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public List<Training> getTrainings(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select t from Training t where t.trainer.user.username = :username", Training.class)
                    .setParameter("username", username)
                    .getResultList();
        }
    }
}
