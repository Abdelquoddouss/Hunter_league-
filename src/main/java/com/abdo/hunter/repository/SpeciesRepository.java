package com.abdo.hunter.repository;

import com.abdo.hunter.domain.entity.Species;
import com.abdo.hunter.domain.enums.SpeciesType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {

    Page<Species> findByCategory(SpeciesType category, Pageable pageable);


    Page<Species> findAll(Pageable pageable);

    boolean existsByName(String name);
}
