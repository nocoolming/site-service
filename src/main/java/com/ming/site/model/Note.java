package com.ming.site.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Note implements IdEntity{
    @Id
    private long id;
    private String title;
    private String keywords;
    private String description;
    private String content;

    private String language;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;

    @ManyToOne
    @JoinColumn(name="create_user_id")
    private User createUser;

    @ManyToOne
    @JoinColumn(name="upgrade_user_id")
    private  User upgradeUser;

    @ManyToOne
    @JoinColumn(name="site_id")
    private  Site site;

    @ManyToOne
    @JoinColumn(name="category_id")
    private  Category category;

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
