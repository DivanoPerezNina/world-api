package com.divano.world_api.country;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
    // Aquí ya tienes métodos como findAll(), findById(), etc.
    Page<Country> findByContinent(String continent, Pageable pageable);
    Page<Country> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Country> findByPopulationGreaterThanEqual(Integer minPopulation, Pageable pageable);
}