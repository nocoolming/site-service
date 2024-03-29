package com.ming.site.model;



import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.RelationManyToMany;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Role
        implements IdLongPrimaryKey {
    public Role() {
        this.createAt = this.upgradeAt = LocalDateTime.now();
    }

    @Id
    private long id;
    private String title;
    private String summary;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;

    private long createUserId;
    private long upgradeUserId;
    private User createUser;

    private User upgradeUser;


    private List<Permission> permissions;

    @RelationManyToMany(
            joinTable = "user_role", // 中间表
            selfField = "id", joinSelfColumn = "role_id",
            targetField = "id", joinTargetColumn = "user_id"
    )
    private List<User> users;

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
