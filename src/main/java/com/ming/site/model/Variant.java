package com.ming.site.model;

import com.mybatisflex.annotation.Id;

import java.math.BigDecimal;

public class Variant
        implements IdLongPrimaryKey {
    @Id
    private Long id;
    private BigDecimal price;
    private int quantity;
    private long productId;

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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
