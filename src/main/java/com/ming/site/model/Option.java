package com.ming.site.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.RelationOneToMany;

import java.util.List;

public class Option implements IdLongPrimaryKey {
    @Id
    private Long id;
    private String title;
    private Long productId;
    private Product product;
    @RelationOneToMany(selfField = "id", targetField = "optionId")
    private List<Value> values;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
