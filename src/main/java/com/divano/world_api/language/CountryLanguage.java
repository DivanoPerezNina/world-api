package com.divano.world_api.language;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countrylanguage")
@IdClass(CountryLanguageId.class) // Aquí enlazamos la llave compuesta
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryLanguage {

    @Id
    @Column(name = "CountryCode")
    private String countryCode;

    @Id
    @Column(name = "Language")
    private String language;

    @Column(name = "IsOfficial")
    private String isOfficial;

    @Column(name = "Percentage")
    private Double percentage;
}