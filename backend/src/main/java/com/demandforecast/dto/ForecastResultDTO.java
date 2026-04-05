package com.demandforecast.dto;

public class ForecastResultDTO {
    private Long product_id;
    private String next_month;
    private Double prediction;

    public ForecastResultDTO(Long product_id, String next_month, Double prediction) {
        this.product_id = product_id;
        this.next_month = next_month;
        this.prediction = prediction;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getNext_month() {
        return next_month;
    }

    public void setNext_month(String next_month) {
        this.next_month = next_month;
    }

    public Double getPrediction() {
        return prediction;
    }

    public void setPrediction(Double prediction) {
        this.prediction = prediction;
    }
}


