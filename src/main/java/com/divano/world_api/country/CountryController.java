package com.divano.world_api.country;

import com.divano.world_api.language.CountryLanguageDTO;
import com.divano.world_api.language.CountryLanguageMapper;
import com.divano.world_api.language.CountryLanguageRepository;
import com.divano.world_api.city.CityDTO;
import com.divano.world_api.city.CityMapper;
import com.divano.world_api.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import com.divano.world_api.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryLanguageRepository languageRepository;

    @Autowired
    private CountryLanguageMapper languageMapper;

    @GetMapping
    public Page<CountryDTO> getCountries(
            @RequestParam(required = false) String continent,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Integer minPopulation,
            Pageable pageable) {

        if (search != null && !search.isBlank()) {
            return countryRepository.findByNameContainingIgnoreCase(search, pageable).map(countryMapper::toDto);
        }
        if (continent != null && !continent.isBlank()) {
            return countryRepository.findByContinent(continent, pageable).map(countryMapper::toDto);
        }
        if (minPopulation != null) {
            return countryRepository.findByPopulationGreaterThanEqual(minPopulation, pageable).map(countryMapper::toDto);
        }

        return countryRepository.findAll(pageable).map(countryMapper::toDto);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable String code) {
        return countryRepository.findById(code)
                .map(countryMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("El país con código " + code + " no fue encontrado."));
    }

    @GetMapping("/{code}/cities")
    public Page<CityDTO> getCitiesByCountryCode(@PathVariable String code, Pageable pageable) {
        return cityRepository.findByCountryCode(code, pageable)
                .map(cityMapper::toDto);
    }

    @GetMapping("/{code}/languages")
    public Page<CountryLanguageDTO> getLanguagesByCountryCode(@PathVariable String code, Pageable pageable) {
        return languageRepository.findByCountryCode(code, pageable)
                .map(languageMapper::toDto);
    }
}