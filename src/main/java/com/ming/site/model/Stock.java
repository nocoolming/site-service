package com.ming.site.model;

import com.mybatisflex.annotation.Id;

import java.math.BigDecimal;

public class Stock
        implements IdLongPrimaryKey {
    @Id
    private Long id;
    private BigDecimal price;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
