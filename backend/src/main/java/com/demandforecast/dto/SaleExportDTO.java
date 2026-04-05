package com.demandforecast.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleExportDTO {

    private Long product_id;
    private Integer quantity;
    private LocalDate date;
    private Integer is_promo;
    private Integer discount_pct;
    private Integer is_holiday;

    public SaleExportDTO(Long product_id, Integer quantity, LocalDate date,
                         Integer is_promo, Integer discount_pct, Integer is_holiday) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.date = date;
        this.is_promo = is_promo;
        this.discount_pct = discount_pct;
        this.is_holiday = is_holiday;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getIs_promo() {
        return is_promo;
    }

    public void setIs_promo(Integer is_promo) {
        this.is_promo = is_promo;
    }

    public Integer getDiscount_pct() {
        return discount_pct;
    }

    public void setDiscount_pct(Integer discount_pct) {
        this.discount_pct = discount_pct;
    }

    public Integer getIs_holiday() {
        return is_holiday;
    }

    public void setIs_holiday(Integer is_holiday) {
        this.is_holiday = is_holiday;
    }
}

