package com.demandforecast.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleDTO {
    private String date;
    private Integer quantity;
    private Double price;

    @JsonProperty("is_promo") // 👈 Faz o Java entender o JSON do Angular/Python
    private Integer isPromo;

    @JsonProperty("discount_pct")
    private Double discountPct;

    @JsonProperty("is_holiday")
    private Integer isHoliday;

    public SaleDTO() {}

    public SaleDTO(String date, Integer quantity, Double price,
                   Integer isPromo, Double discountPct, Integer isHoliday) {
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.isPromo = isPromo;
        this.discountPct = discountPct;
        this.isHoliday = isHoliday;
    }

    public String getDate() { return date; }
    public Integer getQuantity() { return quantity; }
    public Double getPrice() { return price; }
    public Integer getIsPromo() { return isPromo; }
    public Double getDiscountPct() { return discountPct; }
    public Integer getIsHoliday() { return isHoliday; }

    public void setDate(String date) { this.date = date; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(Double price) { this.price = price; }
    public void setIsPromo(Integer isPromo) { this.isPromo = isPromo; }
    public void setDiscountPct(Double discountPct) { this.discountPct = discountPct; }
    public void setIsHoliday(Integer isHoliday) { this.isHoliday = isHoliday; }
}

