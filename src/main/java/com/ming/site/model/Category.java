package com.ming.site.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;
import java.util.List;

public class Category implements IdLongPrimaryKey {
    public Category(){
        this.upgradeAt = this.createAt = LocalDateTime.now();
    }

    private long id;
    private String title;
    private String code;
    private String parentCode;
    private boolean isMenu;
    private String url;
    private String icon;
    private LocalDateTime createAt;

    private LocalDateTime upgradeAt;
    @TableField(exist = false)
    private User createUser;

    @TableField(exist = false)
    private User upgradeUser;

    @TableField(exist = false)
    private List<Note> notes;
    @TableField(exist = false)
    private List<Product> products;
    @TableField(exist = false)
    private List<CategoryLanguage> categoryLanguages;
    @TableField(exist = false)
    private Site site;

    private long createUserId;
    private long upgradeUserId;
    private long siteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public long getUpgradeUserId() {
        return upgradeUserId;
    }

    public void setUpgradeUserId(long upgradeUserId) {
        this.upgradeUserId = upgradeUserId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }
}
