package com.ming.site.model;

import com.mybatisflex.annotation.Id;

public class Domain implements IdLongPrimaryKey {
    @Id
    private Long id;
    private String domain;
    private Long siteId;

//    private Site site;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

//    public Site getSite() {
//        return site;
//    }
//
//    public void setSite(Site site) {
//        this.site = site;
//    }
}
