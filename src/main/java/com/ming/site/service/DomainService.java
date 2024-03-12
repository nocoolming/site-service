package com.ming.site.service;

import com.ming.site.model.Domain;
import com.ming.site.model.Site;

import java.util.List;

public interface DomainService
        extends CrudService<Domain, Long> {

    List<Domain> findBySiteId(long siteId);

    Site findSiteByDomain(String domain);
}
