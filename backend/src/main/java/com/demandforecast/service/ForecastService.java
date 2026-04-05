package com.demandforecast.service;

import com.demandforecast.client.ForecastClient;
import com.demandforecast.client.dto.ForecastResponseDTO;
import com.demandforecast.client.dto.HistoryItemDTO;
import com.demandforecast.client.dto.MultiForecastRequestDTO;
import com.demandforecast.entity.Sale;
import com.demandforecast.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ForecastService {

    private final SaleRepository saleRepository;
    private final ForecastClient forecastClient; // 👈 Seu Feign Client maravilhoso

    public ForecastService(SaleRepository saleRepository, ForecastClient forecastClient) {
        this.saleRepository = saleRepository;
        this.forecastClient = forecastClient;
    }

    public ForecastResponseDTO forecastFromFile(MultiForecastRequestDTO request) {
        System.out.println("🚀 [JAVA] Recebendo CSV do Angular e repassando para o Python...");

        return forecastClient.forecast(request);
    }
}