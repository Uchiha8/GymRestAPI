package com.epam.service;

import com.epam.domain.TrainingType;
import com.epam.repository.TrainingTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingTypeServiceTest {

    @Mock
    private TrainingTypeRepository trainingTypeRepository;

    @InjectMocks
    private TrainingTypeService trainingTypeService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveTrainingType() {
        // Given
        TrainingType trainingType = new TrainingType("Java");

        when(trainingTypeRepository.existsByName("Java")).thenReturn(false);
        when(trainingTypeRepository.save(trainingType)).thenReturn(trainingType);

        // When
        TrainingType result = trainingTypeService.save(trainingType);

        // Then
        assertNotNull(result);
        assertEquals("Java", result.getName());

        verify(trainingTypeRepository, times(1)).existsByName("Java");
        verify(trainingTypeRepository, times(1)).save(trainingType);
    }

    @Test
    void testSaveTrainingTypeWithExistingName() {
        // Given
        TrainingType trainingType = new TrainingType("Java");

        when(trainingTypeRepository.existsByName("Java")).thenReturn(true);

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> trainingTypeService.save(trainingType));

        verify(trainingTypeRepository, times(1)).existsByName("Java");
        verify(trainingTypeRepository, never()).save(any());
    }

    @Test
    void testExistsByName() {
        // Given
        String trainingTypeName = "Java";

        when(trainingTypeRepository.existsByName(trainingTypeName)).thenReturn(true);

        // When
        boolean result = trainingTypeService.existsByName(trainingTypeName);

        // Then
        assertTrue(result);

        verify(trainingTypeRepository, times(1)).existsByName(trainingTypeName);
    }

    @Test
    void testFindAllTrainingTypes() {
        // Given
        TrainingType trainingType1 = new TrainingType("Java");
        TrainingType trainingType2 = new TrainingType("Python");

        List<TrainingType> trainingTypes = Arrays.asList(trainingType1, trainingType2);

        when(trainingTypeRepository.findAll()).thenReturn(trainingTypes);

        // When
        List<TrainingType> result = trainingTypeService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(trainingType1));
        assertTrue(result.contains(trainingType2));

        verify(trainingTypeRepository, times(1)).findAll();
    }

    @Test
    void testFindAllTrainingTypesWithEmptyList() {
        // Given
        List<TrainingType> trainingTypes = List.of();

        when(trainingTypeRepository.findAll()).thenReturn(trainingTypes);

        // When, Then
        assertThrows(RuntimeException.class, () -> trainingTypeService.findAll());

        verify(trainingTypeRepository, times(1)).findAll();
    }

    @Test
    void testFindByName() {
        // Given
        String trainingTypeName = "Java";
        TrainingType trainingType = new TrainingType(trainingTypeName);

        when(trainingTypeRepository.findByName(trainingTypeName)).thenReturn(trainingType);

        // When
        TrainingType result = trainingTypeService.findByName(trainingTypeName);

        // Then
        assertNotNull(result);
        assertEquals(trainingType, result);

        verify(trainingTypeRepository, times(1)).findByName(trainingTypeName);
    }

    @Test
    void testFindByNameWithNonExistingTrainingType() {
        // Given
        String nonExistingTrainingTypeName = "NonExistingType";

        when(trainingTypeRepository.findByName(nonExistingTrainingTypeName)).thenReturn(null);

        // When, Then
        assertThrows(RuntimeException.class, () -> trainingTypeService.findByName(nonExistingTrainingTypeName));

        verify(trainingTypeRepository, times(1)).findByName(nonExistingTrainingTypeName);
    }
}

