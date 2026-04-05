package com.demandforecast.service;

import com.demandforecast.client.ForecastClient;
import com.demandforecast.dto.SaleDTO;
import com.demandforecast.entity.Sale;
import com.demandforecast.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale save(Sale sale) {
        if (sale.getSaleDate() == null) {
            sale.setSaleDate(LocalDate.now());
        }
        return saleRepository.save(sale);
    }

    public List<Sale> findByProduct(Long productId) {
        return saleRepository.findByProductId(productId);
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }
}