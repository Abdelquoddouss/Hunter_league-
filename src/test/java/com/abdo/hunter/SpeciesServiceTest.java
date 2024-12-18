package com.abdo.hunter;


import com.abdo.hunter.domain.entity.Species;
import com.abdo.hunter.domain.enums.Difficulty;
import com.abdo.hunter.domain.enums.SpeciesType;
import com.abdo.hunter.exception.exps.DuplicateResourceException;
import com.abdo.hunter.exception.exps.ResourceNotFoundException;
import com.abdo.hunter.repository.SpeciesRepository;
import com.abdo.hunter.service.HuntService;
import com.abdo.hunter.service.SpeciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SpeciesServiceTest {
    @InjectMocks
    private SpeciesService speciesService;

    @Mock
    private SpeciesRepository speciesRepository;

    @Mock
    private HuntService huntService;

    private Species species;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        species = new Species();
        species.setId(UUID.randomUUID());
        species.setName("Lion");
        species.setCategory(SpeciesType.SEA);
        species.setMinimumWeight(50.0);
        species.setDifficulty(Difficulty.EPIC);
        species.setPoints(100);
    }

    @Test
    void testGetSpeciesByCategory() {
        Page<Species> speciesPage = new PageImpl<>(List.of(species));
        Pageable pageable = Pageable.ofSize(10);

        when(speciesRepository.findByCategory(SpeciesType.SEA, pageable)).thenReturn(speciesPage);

        Page<Species> result = speciesService.getSpeciesByCategory(species, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(speciesRepository, times(1)).findByCategory(SpeciesType.SEA, pageable);
    }

    @Test
    void testAddSpeciesThrowsDuplicateResourceException() {
        when(speciesRepository.existsByName("Lion")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> speciesService.addSpecies(species));
        verify(speciesRepository, never()).save(any());
    }

    @Test
    void testAddSpecies() {
        when(speciesRepository.existsByName("Lion")).thenReturn(false);
        when(speciesRepository.save(species)).thenReturn(species);

        Species result = speciesService.addSpecies(species);

        assertNotNull(result);
        assertEquals("Lion", result.getName());
        verify(speciesRepository, times(1)).save(species);
    }

    @Test
    void testDeleteSpeciesById() {
        when(speciesRepository.findById(species.getId())).thenReturn(Optional.of(species));

        Species result = speciesService.deleteSpeciesById(species);

        assertNotNull(result);
        verify(huntService, times(1)).deleteBySpecies(species.getId());
        verify(speciesRepository, times(1)).deleteById(species.getId());
    }

    @Test
    void testDeleteSpeciesByIdThrowsResourceNotFoundException() {
        when(speciesRepository.findById(species.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> speciesService.deleteSpeciesById(species));
    }

    @Test
    void testUpdateSpecies() {
        Species updatedSpecies = new Species();
        updatedSpecies.setName("Tiger");
        updatedSpecies.setCategory(SpeciesType.SEA);
        updatedSpecies.setMinimumWeight(60.0);
        updatedSpecies.setDifficulty(Difficulty.EPIC);
        updatedSpecies.setPoints(150);

        when(speciesRepository.findById(species.getId())).thenReturn(Optional.of(species));
        when(speciesRepository.save(species)).thenReturn(species);

        Species result = speciesService.updateSpecies(species.getId(), updatedSpecies);

        assertNotNull(result);
        assertEquals("Tiger", result.getName());
        verify(speciesRepository, times(1)).save(species);
    }

    @Test
    void testUpdateSpeciesThrowsResourceNotFoundException() {
        when(speciesRepository.findById(species.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> speciesService.updateSpecies(species.getId(), species));
    }

    @Test
    void testFindById() {
        when(speciesRepository.findById(species.getId())).thenReturn(Optional.of(species));

        Species result = speciesService.findById(species.getId());

        assertNotNull(result);
        assertEquals("Lion", result.getName());
        verify(speciesRepository, times(1)).findById(species.getId());
    }

    @Test
    void testFindByIdThrowsResourceNotFoundException() {
        when(speciesRepository.findById(species.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> speciesService.findById(species.getId()));
    }
}
