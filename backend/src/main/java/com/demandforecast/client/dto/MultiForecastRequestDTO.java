package com.demandforecast.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MultiForecastRequestDTO {

    @JsonProperty("product_id")
    private Long productId;

    private Integer months;

    private List<HistoryItemDTO> history;

    public MultiForecastRequestDTO(Long productId, Integer months, List<HistoryItemDTO> history) {
        this.productId = productId;
        this.months = months;
        this.history = history;
    }
    public MultiForecastRequestDTO() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public List<HistoryItemDTO> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryItemDTO> history) {
        this.history = history;
    }
}
