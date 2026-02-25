package com.divano.world_api.city;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    Page<City> findByCountryCode(String countryCode, Pageable pageable);
    Page<City> findByNameContainingIgnoreCase(String name, Pageable pageable);
}