package com.divano.world_api.language;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryLanguageId implements Serializable {
    private String countryCode;
    private String language;
}