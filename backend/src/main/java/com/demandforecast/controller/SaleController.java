package com.demandforecast.controller;

import com.demandforecast.client.ForecastClient;
import com.demandforecast.client.dto.HistoryItemDTO;
import com.demandforecast.client.dto.MultiForecastRequestDTO;
import com.demandforecast.dto.SaleDTO;
import com.demandforecast.dto.SaleExportDTO;
import com.demandforecast.entity.Sale;
import com.demandforecast.repository.SaleRepository;
import com.demandforecast.service.SaleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;
    private final ForecastClient forecastClient;

    public SaleController(SaleService saleService, ForecastClient forecastClient) {
        this.saleService = saleService;
        this.forecastClient = forecastClient;
    }

    // 🔹 EXPORTA TODO HISTÓRICO PARA O PYTHON TREINAR
    @GetMapping("/export")
    public List<SaleExportDTO> exportAllSales() {
        return saleService.findAll()
                .stream()
                .map(sale -> new SaleExportDTO(
                        sale.getProduct().getId(),
                        sale.getQuantity(),
                        sale.getSaleDate(),
                        0, 0, 0
                ))
                .toList();
    }

    // 🔹 EXPORTA HISTÓRICO DE UM PRODUTO
    @GetMapping("/export/{productId}")
    public List<SaleExportDTO> exportByProduct(@PathVariable Long productId) {
        return saleService.findByProduct(productId)
                .stream()
                .map(sale -> new SaleExportDTO(
                        sale.getProduct().getId(),
                        sale.getQuantity(),
                        sale.getSaleDate(),
                        0, 0, 0
                ))
                .toList();
    }

    // 🔹 TREINAR → Angular chama este endpoint
    @PostMapping("/train")
    public void train() {

        List<SaleDTO> dtos = saleService.findAll()
                .stream()
                .map(s -> new SaleDTO(
                        s.getSaleDate().toString(),
                        s.getQuantity(),
                        s.getPrice(),
                        s.getIsPromo(),
                        s.getDiscountPct(),
                        s.getIsHoliday()
                ))
                .toList();

        forecastClient.trainModel(dtos);
    }

    // 🔹 PREVER → Angular chama → Java chama Python
    @PostMapping("/predict/{productId}")
    public Object predict(@PathVariable Long productId, @RequestParam int months) {

        List<Sale> history = saleService.findByProduct(productId);

        List<HistoryItemDTO> dtos = history.stream()
                .map(s -> new HistoryItemDTO(
                        s.getSaleDate().toString(),
                        s.getQuantity(),
                        s.getPrice(),
                        s.getIsPromo(),
                        s.getDiscountPct(),
                        s.getIsHoliday()
                ))
                .toList();

        MultiForecastRequestDTO req = new MultiForecastRequestDTO(productId, months, dtos);

        return forecastClient.forecast(req);
    }
}