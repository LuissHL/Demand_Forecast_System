package com.demandforecast.client.dto;

import com.demandforecast.dto.SaleDTO; // Importe o seu SaleDTO
import java.util.List;

public class ForecastRequestDTO {
    private Long productId;

    // 👇 O Angular manda os dias pra cá!
    private Integer months;

    // 👇 A lista agora usa o seu SaleDTO atualizado
    private List<SaleDTO> history;

    public ForecastRequestDTO() {}

    // Getters e Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getMonths() { return months; }
    public void setMonths(Integer months) { this.months = months; }

    public List<SaleDTO> getHistory() { return history; }
    public void setHistory(List<SaleDTO> history) { this.history = history; }
}