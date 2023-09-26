package com.ming.site.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Note implements IdEntity {
    public Note(){
        this.createAt = this.upgradeAt = LocalDateTime.now();
    }
    @Id
    private long id;
    private String title;
    private String keywords;
    private String description;
    private String content;
 private  String slateContent;
    private String language;
    private String icon;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;
//    @JsonIgnoreProperties(value = {"notesOfCreateUser"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_id")
    private User createUser;
//    @JsonIgnoreProperties(value = {"notesOfUpgradeUser"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upgrade_user_id")
    private User upgradeUser;
    //    @JsonIgnore
//    @JsonIgnoreProperties(value = {"notes"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;

//    @JsonIgnoreProperties(value = {"notes"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

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

    public String getSlateContent() {
        return slateContent;
    }

    public void setSlateContent(String slateContent) {
        this.slateContent = slateContent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
