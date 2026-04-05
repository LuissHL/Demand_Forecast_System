package com.demandforecast.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ForecastResponseDTO {

        @JsonProperty("product_id")
        private Long productId;

        private List<ForecastItemDTO> predictions;

    public ForecastResponseDTO(Long productId, List<ForecastItemDTO> predictions) {
        this.productId = productId;
        this.predictions = predictions;
    }

    public ForecastResponseDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<ForecastItemDTO> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<ForecastItemDTO> predictions) {
        this.predictions = predictions;
    }
}

