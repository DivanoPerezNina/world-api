package com.divano.world_api.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;

    @GetMapping
    public Page<CityDTO> getCities(
            @RequestParam(required = false) String search,
            Pageable pageable) {

        if (search != null && !search.isBlank()) {
            return cityRepository.findByNameContainingIgnoreCase(search, pageable).map(cityMapper::toDto);
        }

        return cityRepository.findAll(pageable).map(cityMapper::toDto);
    }
}