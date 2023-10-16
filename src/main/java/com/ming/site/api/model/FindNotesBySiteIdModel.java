package com.ming.site.api.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class FindNotesBySiteIdModel {
//    public FindNotesBySiteIdModel(long siteId, LocalDateTime begin){
//        this.siteId = siteId;
//        this.begin = begin;
//    }
    private long siteId;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime begin;

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }
}
