package com.ming.site.service;

import com.ming.site.model.Site;

import java.util.Optional;

public interface SiteService
        extends CrudService<Site, Long> {
    Optional<Site> findByDomain(String domain);
}
