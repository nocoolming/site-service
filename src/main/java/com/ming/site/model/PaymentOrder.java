package com.ming.site.model;


import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

public class PaymentOrder
        implements IdLongPrimaryKey {

    private long id;
    private String channel;
    private LocalDateTime createAt;


    @TableField(exist = false)
    private Order order;

    @TableField(exist = false)
    private Site site;

    private long orderId;
    private long siteId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
