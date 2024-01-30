package com.epam.repository;

import com.epam.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.persist(user);
            return user;
        }
    }

    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username",
                    User.class
            );
            query.setParameter("username", username);
            if (query.uniqueResult() != null) {
                return query.uniqueResult();
            }
            return null;
        }
    }

    public boolean existsByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(u) > 0 from User u where u.username = :username", Boolean.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }
    }

    public void updatePassword(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    public User update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
            return user;
        }
    }

}
