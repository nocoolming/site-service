package com.ming.site.model;

import com.mybatisflex.annotation.Id;

import java.util.List;

public class Variant implements IdLongPrimaryKey {
    @Id
    private Long id;
    private String title;
    private Long productId;
    private Product product;
    private List<VariantValue> variantValueList;

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

    public List<VariantValue> getVariantValueList() {
        return variantValueList;
    }

    public void setVariantValueList(List<VariantValue> variantValueList) {
        this.variantValueList = variantValueList;
    }
}
