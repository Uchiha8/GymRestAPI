package com.epam.repository;

import com.epam.domain.Trainee;
import com.epam.domain.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TraineeRepositoryTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<Boolean> booleanQuery;

    @Mock
    private Query<Trainee> traineeQuery;

    @Mock
    private Query<Training> trainingQuery;

    @InjectMocks
    private TraineeRepository traineeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void testSaveTrainee() {
        // Given
        Trainee trainee = new Trainee();
        when(session.getTransaction()).thenReturn(transaction);
        // When
        Trainee result = traineeRepository.save(trainee);

        // Then
        assertNotNull(result);
        assertEquals(trainee, result);

        verify(session, times(1)).persist(trainee);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testExistsByUsernameWhenTraineeExists() {
        // Given
        String username = "existingTrainee";

        when(session.createQuery(anyString(), eq(Boolean.class))).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyString())).thenReturn(booleanQuery);
        when(booleanQuery.getSingleResult()).thenReturn(true);

        // When
        boolean result = traineeRepository.existsByUsername(username);

        // Then
        assertTrue(result);

        verify(session, times(1)).createQuery(anyString(), eq(Boolean.class));
        verify(booleanQuery, times(1)).setParameter(anyString(), anyString());
        verify(booleanQuery, times(1)).getSingleResult();
    }

    @Test
    void testFindByUsernameWhenTrainee() {
        // Given
        String username = "existingTrainee";
        Trainee trainee = new Trainee();

        when(session.createQuery(anyString(), eq(Trainee.class))).thenReturn(traineeQuery);
        when(traineeQuery.setParameter(anyString(), anyString())).thenReturn(traineeQuery);
        when(traineeQuery.getSingleResult()).thenReturn(trainee);

        // When
        Trainee result = traineeRepository.findByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(trainee, result);

        verify(session, times(1)).createQuery(anyString(), eq(Trainee.class));
        verify(traineeQuery, times(1)).setParameter(anyString(), anyString());
        verify(traineeQuery, times(1)).getSingleResult();
    }

    @Test
    void testUpdateTrainee() {
        // Given
        Trainee trainee = new Trainee();
        when(session.getTransaction()).thenReturn(transaction);
        // When
        Trainee result = traineeRepository.update(trainee);

        // Then
        assertNotNull(result);
        assertEquals(trainee, result);

        verify(session, times(1)).merge(trainee);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testDeleteTrainee() {
        // Given
        Trainee trainee = new Trainee();
        when(session.getTransaction()).thenReturn(transaction);
        // When
        traineeRepository.delete(trainee);

        // Then
        verify(session, times(1)).remove(trainee);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testUpdateStatus() {
        // Given
        String username = "existingTrainee";
        boolean status = true;

        when(session.createQuery(anyString())).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyBoolean())).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyString())).thenReturn(booleanQuery);
        when(booleanQuery.executeUpdate()).thenReturn(1);
        when(session.getTransaction()).thenReturn(transaction);
        // When
        traineeRepository.updateStatus(username, status);

        // Then
        verify(session, times(1)).createQuery(anyString());
        verify(booleanQuery, times(1)).setParameter(anyString(), anyBoolean());
        verify(booleanQuery, times(1)).setParameter(anyString(), anyString());
        verify(booleanQuery, times(1)).executeUpdate();
        verify(transaction, times(1)).commit();
    }

    @Test
    void testGetTrainings() {
        // Given
        String username = "existingTrainee";
        List<Training> trainingList = Collections.emptyList();

        when(session.createQuery(anyString(), eq(Training.class))).thenReturn(trainingQuery);
        when(trainingQuery.setParameter(anyString(), anyString())).thenReturn(trainingQuery);
        when(trainingQuery.getResultList()).thenReturn(trainingList);

        // When
        List<Training> result = traineeRepository.getTrainings(username);

        // Then
        assertNotNull(result);
        assertEquals(trainingList, result);

        verify(session, times(1)).createQuery(anyString(), eq(Training.class));
        verify(trainingQuery, times(1)).setParameter(anyString(), anyString());
        verify(trainingQuery, times(1)).getResultList();
    }

}
