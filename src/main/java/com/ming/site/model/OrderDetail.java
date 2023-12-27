package com.ming.site.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.RelationManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderDetail
        implements IdLongPrimaryKey {
    public OrderDetail() {
        this.createAt = this.upgradeAt = LocalDateTime.now();
    }

    @Id
    private Long id;

    private String title;
    private BigDecimal price;
    private int quantity;
    private BigDecimal subtotal;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;
    private Long createUserId;
    private Long orderId;

    private String icon;
    private Long productId;
    private Long stockId;
    private User createUser;

    private Order order;

    @RelationManyToOne(selfField = "productId", targetField = "id")
    private Product product;
    @RelationManyToOne(selfField = "stockId", targetField = "id")
    private Variant variant;


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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpgradeAt() {
        return upgradeAt;
    }

    public void setUpgradeAt(LocalDateTime upgradeAt) {
        this.upgradeAt = upgradeAt;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Variant getStock() {
        return variant;
    }

    public void setStock(Variant variant) {
        this.variant = variant;
    }
}
