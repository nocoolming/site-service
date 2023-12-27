package com.ming.site.model;

import java.io.Serializable;

public class VariantValue
        implements Serializable {
    private Long variantId;
    private Long valueId;

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
}
