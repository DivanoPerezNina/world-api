package com.divano.world_api.country;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryMapperTest {

    // Instancia manual para testear sin levantar todo Spring
    private final CountryMapper mapper = Mappers.getMapper(CountryMapper.class);

    @Test
    public void shouldMapCountryToCountryDTO() {
        Country country = new Country("PER", "Peru", "South America", 1285216.0, 25662000);

        CountryDTO dto = mapper.toDto(country);

        assertNotNull(dto);
        assertEquals("PER", dto.getCode());
        assertEquals("Peru", dto.getName());
        assertEquals("South America", dto.getContinent());
        assertEquals(25662000, dto.getPopulation());
    }
}