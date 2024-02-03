package com.epam.repository;

import com.epam.domain.Trainee;
import com.epam.domain.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TraineeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public TraineeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Trainee save(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(trainee);
            session.getTransaction().commit();
            return trainee;
        }
    }

    public boolean existsByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(t) > 0 from Trainee t where t.user.username = :username", Boolean.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }
    }

    public Trainee findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select t from Trainee t where t.user.username = :username", Trainee.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }
    }

    public Trainee update(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(trainee);
            session.getTransaction().commit();
            return trainee;
        }
    }

    public void delete(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(trainee);
            session.getTransaction().commit();
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
            return session.createQuery("select t from Training t where t.trainee.user.username = :username", Training.class)
                    .setParameter("username", username)
                    .getResultList();
        }
    }


}
