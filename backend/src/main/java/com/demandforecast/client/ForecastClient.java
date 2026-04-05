package com.demandforecast.client;

import com.demandforecast.client.dto.ForecastResponseDTO;
import com.demandforecast.client.dto.MultiForecastRequestDTO;
import com.demandforecast.dto.ProductHistoryDTO;
import com.demandforecast.dto.ForecastResultDTO;
import com.demandforecast.dto.SaleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "forecastClient", url = "http://localhost:8000")
public interface ForecastClient {

    @PostMapping("/predict/multi")
    ForecastResponseDTO forecast(@RequestBody MultiForecastRequestDTO request);

    @PostMapping("/train")
    void trainModel(@RequestBody List<SaleDTO> sales);

}
