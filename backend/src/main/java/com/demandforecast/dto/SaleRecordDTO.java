package com.demandforecast.dto;

public class SaleRecordDTO {
    private String date;
    private Integer quantity;

    public SaleRecordDTO() {
    }

    public SaleRecordDTO(String date, Integer quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
