package com.ming.site.service;

import com.ming.site.model.Site;


public interface SiteService
        extends CrudService<Site, Long> {

    Site create(Site site);
    Site findByDomain(String domain);
}
