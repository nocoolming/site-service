package com.ming.site.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Role
        implements IdLongPrimaryKey {
    public Role() {
        this.createAt = this.upgradeAt = LocalDateTime.now();
    }

    private long id;
    private String title;
    private String summary;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;

    private long createUserId;
    private long upgradeUserId;
    private long siteId;

    @TableField(exist = false)
    private User createUser;

    @TableField(exist = false)
    private User upgradeUser;

    @TableField(exist = false)
    private Site site;

    @TableField(exist = false)
    private List<Permission> permissions;

    @TableField(exist = false)
    private Set<User> users;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
