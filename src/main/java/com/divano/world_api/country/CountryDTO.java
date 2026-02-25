package com.divano.world_api.country;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private String code;
    private String name;
    private String continent;
    private Integer population;
}