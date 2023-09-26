package com.ming.site.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({
        "notes",
        "categories",
        "products",
        "users",
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Site implements IdEntity {
    @Id
    private long id;
    private String title;
    private String keywords;
    private String description;

    private String language;
    private String domain;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;

//    @JsonIgnoreProperties(value={"site"})
    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
    private List<Product> products;


//    @JsonIgnoreProperties(value={"categories"})
    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
    private List<Category> categories;

//    @JsonIgnoreProperties(value={"notes"})
    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
    private List<User> users;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getUpgradeAt() {
        return upgradeAt;
    }

    public void setUpgradeAt(LocalDateTime upgradeAt) {
        this.upgradeAt = upgradeAt;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
