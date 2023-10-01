package com.ming.site.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

public class ProductImage implements IdLongPrimaryKey {
    public ProductImage() {
        this.createAt = LocalDateTime.now();
    }

    private long id;

    private String url;
    private String alt;
    private LocalDateTime createAt;


    @TableField(exist = false)
    private User createUser;

    @TableField(exist = false)
    private Product product;

    private long createUserId;

    private Long productId;

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
