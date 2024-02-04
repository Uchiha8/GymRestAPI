package com.epam.repository;
import com.epam.domain.Trainee;
import com.epam.domain.Trainer;
import com.epam.domain.Training;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
public class TrainingRepositoryTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<Boolean> booleanQuery;

    @InjectMocks
    private TrainingRepository trainingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void testSaveTraining() {
        // Given
        Training training = new Training();

        when(session.getTransaction()).thenReturn(transaction);
        // When
        Training result = trainingRepository.save(training);

        // Then
        assertNotNull(result);
        assertEquals(training, result);

        verify(session, times(1)).persist(training);
        verify(transaction, times(1)).commit();
    }

    @Test
    void testExistsByName() {
        // Given
        String name = "existingTraining";

        when(session.createQuery(anyString(), eq(Boolean.class))).thenReturn(booleanQuery);
        when(booleanQuery.setParameter(anyString(), anyString())).thenReturn(booleanQuery);
        when(booleanQuery.getSingleResult()).thenReturn(true);
        when(session.getTransaction()).thenReturn(transaction);
        // When
        boolean result = trainingRepository.existsByName(name);

        // Then
        assertTrue(result);

        verify(session, times(1)).createQuery(anyString(), eq(Boolean.class));
        verify(booleanQuery, times(1)).setParameter(anyString(), anyString());
        verify(booleanQuery, times(1)).getSingleResult();
    }

}
