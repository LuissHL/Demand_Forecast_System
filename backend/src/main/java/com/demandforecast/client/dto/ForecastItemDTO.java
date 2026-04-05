package com.demandforecast.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastItemDTO {

    private String date;

    private Double prediction;

    private Double min;

    private Double max;

    private Double confidence;

    public ForecastItemDTO() {}

    public ForecastItemDTO(String date, Double prediction, Double min, Double max, Double confidence) {
        this.date = date;
        this.prediction = prediction;
        this.min = min;
        this.max = max;
        this.confidence = confidence;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrediction() {
        return prediction;
    }

    public void setPrediction(Double prediction) {
        this.prediction = prediction;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}
