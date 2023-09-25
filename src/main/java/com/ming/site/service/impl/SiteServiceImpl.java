package com.ming.site.service.impl;

import com.ming.site.model.Category;
import com.ming.site.model.Site;
import com.ming.site.repository.SiteRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteServiceImpl
    extends AbstractService<Site, Long, SiteRepository>
    implements SiteService {
    private  static final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Override
    public Optional<Site> findByDomain(String domain) {
        return  repository.findByDomain(domain);
    }
}
