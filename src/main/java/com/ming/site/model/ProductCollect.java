package com.ming.site.model;

import com.mybatisflex.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProductCollect
        implements IdLongPrimaryKey, Serializable {

    @Id
    private long id;
    private String title;
    private String keywords;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime upgradeAt;
    private long createUserId;
    private long upgradeUserId;;
    private long siteId;

    private User createUser;
    private User upgradeUser;
    private Site site;
//    private List
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
