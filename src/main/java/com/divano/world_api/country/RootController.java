package com.divano.world_api.country;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> welcome() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("api", "World API");
        response.put("status", "Running");
        response.put("endpoints", List.of(
                "/countries",
                "/countries/{code}",
                "/countries/{code}/cities",
                "/countries/{code}/languages"
        ));
        return response;
    }
}