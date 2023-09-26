package com.ming.site.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"order\"")
//@JsonIgnoreProperties({""
//})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order
        implements IdEntity {
    @Id
    private long id;
    private BigDecimal orderTotal;
    private BigDecimal receivables;
    private BigDecimal actualPayments;
    private String contact;
    private String mobile;
    private String tel;
    private String address;
    private String country;
    private String province;
    private String street;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;

    @ManyToOne
    @JoinColumn(name = "create_user_id")
    private User createUser;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentOrder paymentOrder;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public BigDecimal getReceivables() {
        return receivables;
    }

    public void setReceivables(BigDecimal receivables) {
        this.receivables = receivables;
    }

    public BigDecimal getActualPayments() {
        return actualPayments;
    }

    public void setActualPayments(BigDecimal actualPayments) {
        this.actualPayments = actualPayments;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public PaymentOrder getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(PaymentOrder paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
