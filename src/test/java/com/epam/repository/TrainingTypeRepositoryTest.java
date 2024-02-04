package com.epam.repository;

import com.epam.domain.TrainingType;
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

public class TrainingTypeRepositoryTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<TrainingType> trainingTypeQuery;

    @Mock
    private Query<Boolean> booleanQuery;

    @InjectMocks
    private TrainingTypeRepository trainingTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void testSaveTrainingType() {
        // Given
        TrainingType trainingType = new TrainingType();

        when(session.getTransaction()).thenReturn(transaction);
        // When
        TrainingType result = trainingTypeRepository.save(trainingType);

        // Then
        assertNotNull(result);
        assertEquals(trainingType, result);

        verify(session, times(1)).persist(trainingType);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testFindAllTrainingTypes() {
        // Given
        List<TrainingType> trainingTypeList = Collections.emptyList();

        when(session.createQuery(anyString(), eq(TrainingType.class))).thenReturn(trainingTypeQuery);
        when(trainingTypeQuery.getResultList()).thenReturn(trainingTypeList);

        // When
        List<TrainingType> result = trainingTypeRepository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(trainingTypeList, result);

        verify(session, times(1)).createQuery(anyString(), eq(TrainingType.class));
        verify(trainingTypeQuery, times(1)).getResultList();
    }

    @Test
    void testFindByNameWhenTrainingTypeExists() {
        // Given
        String name = "existingTrainingType";
        TrainingType trainingType = new TrainingType();

        when(session.createQuery(anyString(), eq(TrainingType.class))).thenReturn(trainingTypeQuery);
        when(trainingTypeQuery.setParameter(anyString(), anyString())).thenReturn(trainingTypeQuery);
        when(trainingTypeQuery.getSingleResult()).thenReturn(trainingType);

        // When
        TrainingType result = trainingTypeRepository.findByName(name);

        // Then
        assertNotNull(result);
        assertEquals(trainingType, result);

        verify(session, times(1)).createQuery(anyString(), eq(TrainingType.class));
        verify(trainingTypeQuery, times(1)).setParameter(anyString(), anyString());
        verify(trainingTypeQuery, times(1)).getSingleResult();
    }

    @Test
    void testExistsByNameWhenTrainingTypeExists() {
        // Given
        String name = "existingTrainingType";

        when(session.createQuery(anyString(), eq(Boolean.class))).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyString())).thenReturn(booleanQuery);
        when(booleanQuery.getSingleResult()).thenReturn(true);

        // When
        boolean result = trainingTypeRepository.existsByName(name);

        // Then
        assertTrue(result);

        verify(session, times(1)).createQuery(anyString(), eq(Boolean.class));
        verify(booleanQuery, times(1)).setParameter(anyString(), anyString());
        verify(booleanQuery, times(1)).getSingleResult();
    }
}
