package com.demandforecast.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductHistoryDTO {
    @JsonProperty("product_id")      // serializa como product_id
    private Long product_id;
    private List<SaleRecordDTO> history;

    public ProductHistoryDTO() {
    }

    public ProductHistoryDTO(Long product_id, List<SaleRecordDTO> history) {
        this.product_id = product_id;
        this.history = history;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public List<SaleRecordDTO> getHistory() {
        return history;
    }

    public void setHistory(List<SaleRecordDTO> history) {
        this.history = history;
    }
}
