package com.demandforecast.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class HistoryItemDTO {

    @JsonProperty("date")
    private String date;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("is_promo")
    private Integer isPromo;

    @JsonProperty("discount_pct")
    private Double discountPct;

    @JsonProperty("is_holiday")
    private Integer isHoliday;

    public HistoryItemDTO() {}

    // construtor completo
    public HistoryItemDTO(String date, Integer quantity, Double price,
                          Integer isPromo, Double discountPct, Integer isHoliday) {
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.isPromo = isPromo;
        this.discountPct = discountPct;
        this.isHoliday = isHoliday;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(Integer isPromo) {
        this.isPromo = isPromo;
    }

    public Double getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(Double discountPct) {
        this.discountPct = discountPct;
    }

    public Integer getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Integer isHoliday) {
        this.isHoliday = isHoliday;
    }
}
