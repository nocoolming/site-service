package com.ming.site.model;

import java.time.LocalDateTime;

public class ProductImage {
    private int id;
    private String url;
    private String alt;
    private String localPath;
    private int productId;

    private String createUserId;
        private LocalDateTime createAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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