package com.divano.world_api.language;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageId> {
    Page<CountryLanguage> findByCountryCode(String countryCode, Pageable pageable);
}