package com.ming.site.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Category implements IdEntity{
    @Id
    private long id;
    private String title;
    private String code;
    private String parentCode;
    private LocalDateTime createAt;

    private LocalDateTime upgradeAt;
    @ManyToOne
    @JoinColumn(name="create_user_id")
    private User createUser;

    @ManyToOne
    @JoinColumn(name="upgrade_user_id")
    private  User upgradeUser;

    @OneToMany(mappedBy = "category")
    private List<Note> notes;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @OneToMany(mappedBy = "category")
    private List<CategoryLanguage> categoryLanguages;

    @ManyToOne
    @JoinColumn(name="site_id")
    private Site site;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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

    public User getUpgradeUser() {
        return upgradeUser;
    }

    public void setUpgradeUser(User upgradeUser) {
        this.upgradeUser = upgradeUser;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<CategoryLanguage> getCategoryLanguages() {
        return categoryLanguages;
    }

    public void setCategoryLanguages(List<CategoryLanguage> categoryLanguages) {
        this.categoryLanguages = categoryLanguages;
    }


}
