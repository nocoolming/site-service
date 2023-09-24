package com.ming.site.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class ProductImage implements IdEntity {
    @Id
    private long id;

    private String url;
    private String alt;
    private LocalDateTime createAt;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="create_user_id")
    private User createUser;

    @ManyToOne
    @JoinColumn(name="site_id")
    private Site site;

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




    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

//    id Int      @id@default(autoincrement())
//        url String
//        alt String
//        localPath String
//        productId Int
//        product Product@relation(fields:[productId], references:[id])
//  createUserId String
//          createUser User@relation(fields:[createUserId], references:[id], onDelete:SetDefault)
//  createAt DateTime
//          updateAt DateTime