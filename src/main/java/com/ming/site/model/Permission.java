package com.ming.site.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Permission
implements IdEntity{
    @Id
    private long id;
    private String title;
    private String summary;
    private boolean isMenu;
    private boolean isUrl;
    private String url;
    private long createUserId;
    private long upgradeUserId;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;
    private long siteId;

    @ManyToOne
    @JoinColumn(name="create_user_id", nullable=true)
    private User createUser;

    @ManyToOne
    @JoinColumn(name="upgrade_user_id", nullable=true)
    private  User upgradeUser;

    @ManyToOne
    @JoinColumn(name="site_id", nullable = true)
    private Site site;
    @ManyToMany(mappedBy = "roles")
    private List<Role> roles;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public boolean isUrl() {
        return isUrl;
    }

    public void setUrl(boolean url) {
        isUrl = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
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
}
