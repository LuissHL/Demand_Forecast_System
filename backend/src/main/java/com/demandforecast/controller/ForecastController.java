package com.demandforecast.controller;

import com.demandforecast.client.dto.ForecastResponseDTO;
import com.demandforecast.client.dto.MultiForecastRequestDTO;
import com.demandforecast.service.ForecastService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200") // Permite o Angular conversar com o Java
@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    // (CSV / Angular -> Java -> Python)
    @PostMapping
    public ForecastResponseDTO forecastFromFile(
            @RequestBody MultiForecastRequestDTO request
    ) {
        // O Java recebe o JSON da tabela do Angular e só encaminha!
        return forecastService.forecastFromFile(request);
    }
}