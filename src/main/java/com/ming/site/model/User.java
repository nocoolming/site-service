package com.ming.site.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@TableName("\"user\"")
public class User implements IdLongPrimaryKey {

    private long id;
    private String username;
    private String password;
    private String mail;

    private String firstName;
    private String lastName;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;

    @TableField(exist = false)
    private List<Product> productsOfCreateUser;

    @TableField(exist = false)
    private List<Product> productsOfUpgradeUser;

    @TableField(exist = false)
    private List<Category> categories;

    @TableField(exist = false)
    private List<Note> notesOfCreateUser;

    @TableField(exist = false)
    private List<Note> notesOfUpgradeUser;

    @TableField(exist = false)
    private List<Order> orders;

    @TableField(exist = false)
    private Set<Role> roles;

    @TableField(exist = false)
    private Site site;

    @TableField(exist = false)
    private List<ProductImage> productImageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProductsOfCreateUser() {
        return productsOfCreateUser;
    }

    public void setProductsOfCreateUser(List<Product> productsOfCreateUser) {
        this.productsOfCreateUser = productsOfCreateUser;
    }

    public List<Product> getProductsOfUpgradeUser() {
        return productsOfUpgradeUser;
    }

    public void setProductsOfUpgradeUser(List<Product> productsOfUpgradeUser) {
        this.productsOfUpgradeUser = productsOfUpgradeUser;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Note> getNotesOfCreateUser() {
        return notesOfCreateUser;
    }

    public void setNotesOfCreateUser(List<Note> notesOfCreateUser) {
        this.notesOfCreateUser = notesOfCreateUser;
    }

    public List<Note> getNotesOfUpgradeUser() {
        return notesOfUpgradeUser;
    }

    public void setNotesOfUpgradeUser(List<Note> notesOfUpgradeUser) {
        this.notesOfUpgradeUser = notesOfUpgradeUser;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }
}
