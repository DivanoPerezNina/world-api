package com.divano.world_api.language;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryLanguageMapper {
    CountryLanguageDTO toDto(CountryLanguage countryLanguage);
}