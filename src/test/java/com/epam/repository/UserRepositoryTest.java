package com.epam.repository;

import com.epam.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;
    @Mock
    private Query<User> query;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(session.beginTransaction()).thenReturn(transaction);
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    void testSaveUser() {
        // Given
        User user = new User();
        // When
        userRepository.save(user);
        // Then
        verify(session, times(1)).persist(user);
    }

    @Test
    void testFindByUsername() {
        // Given
        String username = "existingUser";
        User user = new User("firstName", "lastName", username, "password", true);

        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(user);

        // When
        User result = userRepository.findByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void testUpdatePassword() {
        // Given
        User user = new User("firstName", "lastName", "testUser", "password", true);

        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);

        // When
        userRepository.updatePassword(user);

        // Then
        verify(session, times(1)).merge(user);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testExistsByUsernameWhenUserExists() {
        // Given
        String username = "existingUser";
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        Query<Boolean> query1 = mock(Query.class);
        when(session.createQuery(anyString(), eq(Boolean.class))).thenReturn(query1);
        when(query1.setParameter(anyString(), anyString())).thenReturn(query1);
        when(query1.getSingleResult()).thenReturn(true);
        // When
        boolean result = userRepository.existsByUsername(username);

        // Then
        assertTrue(result);

        verify(session, times(1)).createQuery(anyString(), eq(Boolean.class));
        verify(query, times(0)).setParameter(anyString(), anyString());
        verify(query, times(0)).getSingleResult();
    }

    @Test
    void testUpdateUser() {
        // Given
        User user = new User("firstName", "lastName", "testUser", "password", true);

        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
        // When
        User result = userRepository.update(user);

        // Then
        assertNotNull(result);
        assertEquals(user, result);

        verify(session, times(1)).merge(user);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testFindByUsernameWhenUserDoesNotExist() {
        // Given
        String nonExistingUsername = "nonExistingUser";

        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(null);
        when(session.getTransaction()).thenReturn(transaction);
        // When
        User result = userRepository.findByUsername(nonExistingUsername);

        // Then
        assertNull(result);

        verify(session, times(1)).createQuery(anyString(), eq(User.class));
        verify(query, times(1)).setParameter(anyString(), anyString());
        verify(query, times(1)).uniqueResult();
    }

}
