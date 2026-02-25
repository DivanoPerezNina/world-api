package com.divano.world_api.country;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOkWhenGetCountries() throws Exception {
        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnListOfCountriesFromDatabase() throws Exception {
        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThan(0)))) // Esperamos que la lista no esté vacía
                .andExpect(jsonPath("$.content[0].name").exists()); // Esperamos que el primer objeto tenga un campo "name"
    }

    @Test
    public void shouldReturnPeruWhenGetCountryByCodePER() throws Exception {
        mockMvc.perform(get("/countries/PER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Peru"))
                .andExpect(jsonPath("$.code").value("PER"));
    }

    @Test
    public void shouldReturn404WhenCountryNotFound() throws Exception {
        mockMvc.perform(get("/countries/XYZ"))
                .andExpect(status().isNotFound())
                // Ahora exigimos que el error tenga esta estructura
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void shouldReturnPaginatedCountries() throws Exception {
        mockMvc.perform(get("/countries")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(10))) // Spring Page envuelve los datos en "content"
                .andExpect(jsonPath("$.totalElements").exists());
    }

    @Test
    public void shouldReturnCitiesForGivenCountryCode() throws Exception {
        mockMvc.perform(get("/countries/PER/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").exists());
    }

    @Test
    public void shouldReturnLanguagesForGivenCountryCode() throws Exception {
        // Verificamos que al buscar los idiomas de Perú (PER), nos devuelva una lista paginada
        mockMvc.perform(get("/countries/PER/languages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].language").exists());
    }

    @Test
    public void shouldReturnCountriesFilteredByContinent() throws Exception {
        mockMvc.perform(get("/countries")
                        .param("continent", "Asia") // Nuestro nuevo filtro
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.content[0].continent").value("Asia"));
    }

    @Test
    public void shouldReturnCountriesMatchingPartialName() throws Exception {
        // Simulamos lo que haría el frontend cuando el usuario tipea "per" en el buscador
        mockMvc.perform(get("/countries")
                        .param("search", "per") // Parámetro genérico de búsqueda
                        .param("size", "5")) // Solo queremos unas cuantas sugerencias
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.content[0].name").value("Peru"));
    }

    @Test
    public void shouldReturnCountriesWithPopulationGreaterThan() throws Exception {
        mockMvc.perform(get("/countries")
                        .param("minPopulation", "100000000") // Más de 100 millones
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.content[0].population", greaterThan(100000000)));
    }
}