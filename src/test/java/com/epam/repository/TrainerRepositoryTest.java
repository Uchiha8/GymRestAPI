package com.epam.repository;

import com.epam.domain.Trainer;
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

public class TrainerRepositoryTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<Boolean> booleanQuery;

    @Mock
    private Query<Trainer> trainerQuery;

    @Mock
    private Query<Training> trainingQuery;

    @InjectMocks
    private TrainerRepository trainerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void testSaveTrainer() {
        // Given
        Trainer trainer = new Trainer();

        when(session.getTransaction()).thenReturn(transaction);
        // When
        Trainer result = trainerRepository.save(trainer);

        // Then
        assertNotNull(result);
        assertEquals(trainer, result);

        verify(session, times(1)).persist(trainer);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testExistsByUsername() {
        // Given
        String username = "existingTrainer";

        when(session.createQuery(anyString(), eq(Boolean.class))).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyString())).thenReturn(booleanQuery);
        when(booleanQuery.getSingleResult()).thenReturn(true);

        // When
        boolean result = trainerRepository.existsByUsername(username);

        // Then
        assertTrue(result);

        verify(session, times(1)).createQuery(anyString(), eq(Boolean.class));
        verify(booleanQuery, times(1)).setParameter(anyString(), anyString());
        verify(booleanQuery, times(1)).getSingleResult();
    }

    @Test
    void testFindByUsername() {
        // Given
        String username = "existingTrainer";
        Trainer trainer = new Trainer();

        when(session.createQuery(anyString(), eq(Trainer.class))).thenReturn(trainerQuery);
        when(trainerQuery.setParameter(anyString(), anyString())).thenReturn(trainerQuery);
        when(trainerQuery.getSingleResult()).thenReturn(trainer);

        // When
        Trainer result = trainerRepository.findByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(trainer, result);

        verify(session, times(1)).createQuery(anyString(), eq(Trainer.class));
        verify(trainerQuery, times(1)).setParameter(anyString(), anyString());
        verify(trainerQuery, times(1)).getSingleResult();
    }

    @Test
    void testUpdateTrainer() {
        // Given
        Trainer trainer = new Trainer();

        when(session.getTransaction()).thenReturn(transaction);
        // When
        Trainer result = trainerRepository.update(trainer);

        // Then
        assertNotNull(result);
        assertEquals(trainer, result);

        verify(session, times(1)).merge(trainer);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testActiveTrainers() {
        // Given
        List<Trainer> trainerList = Collections.emptyList();

        when(session.createQuery(anyString(), eq(Trainer.class))).thenReturn(trainerQuery);
        when(trainerQuery.getResultList()).thenReturn(trainerList);

        // When
        List<Trainer> result = trainerRepository.activeTrainers();

        // Then
        assertNotNull(result);
        assertEquals(trainerList, result);

        verify(session, times(1)).createQuery(anyString(), eq(Trainer.class));
        verify(trainerQuery, times(1)).getResultList();
    }

    @Test
    void testUpdateStatus() {
        // Given
        String username = "existingTrainer";
        boolean status = true;

        when(session.createQuery(anyString())).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyBoolean())).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyString())).thenReturn(booleanQuery);
        when(booleanQuery.executeUpdate()).thenReturn(1);
        when(session.getTransaction()).thenReturn(transaction);
        // When
        trainerRepository.updateStatus(username, status);

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
        String username = "existingTrainer";
        List<Training> trainingList = Collections.emptyList();

        when(session.createQuery(anyString(), eq(Training.class))).thenReturn(trainingQuery);
        when(trainingQuery.setParameter(anyString(), anyString())).thenReturn(trainingQuery);
        when(trainingQuery.getResultList()).thenReturn(trainingList);

        // When
        List<Training> result = trainerRepository.getTrainings(username);

        // Then
        assertNotNull(result);
        assertEquals(trainingList, result);

        verify(session, times(1)).createQuery(anyString(), eq(Training.class));
        verify(trainingQuery, times(1)).setParameter(anyString(), anyString());
        verify(trainingQuery, times(1)).getResultList();
    }
}
