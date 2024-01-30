package com.epam.repository;

import com.epam.domain.Trainee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
