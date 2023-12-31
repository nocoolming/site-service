package com.ming.site.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.RelationManyToMany;

import java.math.BigDecimal;
import java.util.List;

public class Variant
        implements IdLongPrimaryKey {
    @Id
    private Long id;
    private BigDecimal price;
    private int quantity;
    private long productId;

    @RelationManyToMany(
            joinTable = "variant_value",
            selfField = "id", joinSelfColumn = "variant_id",
            targetField = "id", joinTargetColumn = "value_id"
    )
    private List<Value> values;

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

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
