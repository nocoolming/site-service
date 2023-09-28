package com.ming.site.service;

import com.ming.site.model.Site;


public interface SiteService
        extends CrudService<Site, Long> {
    Site findByDomain(String domain);
}
