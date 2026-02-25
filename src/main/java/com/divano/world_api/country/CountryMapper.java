package com.divano.world_api.country;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryMapper {
    CountryDTO toDto(Country country);
    List<CountryDTO> toDtoList(List<Country> countries);
}