package com.divano.world_api.country;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor // Obligatorio para JPA
@AllArgsConstructor // Útil para crear objetos en tus pruebas
@ToString(of = {"code", "name"}) // Solo imprimimos los campos clave para evitar problemas
public class Country {
    @Id
    @Column(name = "Code")
    private String code;

    @Column(name = "Name")
    private String name;

    @Column(name = "Continent")
    private String continent;

    @Column(name = "SurfaceArea")
    private Double surfaceArea;

    @Column(name = "Population")
    private Integer population;
}