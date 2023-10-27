package com.ming.site.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cart
        implements IdLongPrimaryKey {
    private long id;
    private BigDecimal subtotal;
    private Long createUserId;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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
}
