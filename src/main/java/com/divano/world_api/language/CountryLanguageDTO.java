package com.divano.world_api.language;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryLanguageDTO {
    private String language;
    private String isOfficial;
    private Double percentage;
}