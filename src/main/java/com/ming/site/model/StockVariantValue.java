package com.ming.site.model;

import java.io.Serializable;

public class StockVariantValue
        implements Serializable {
    private Long stockId;
    private Long variantValueId;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getVariantValueId() {
        return variantValueId;
    }

    public void setVariantValueId(Long variantValueId) {
        this.variantValueId = variantValueId;
    }
}
