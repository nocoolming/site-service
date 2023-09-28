package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.model.Site;
import com.ming.site.repository.SiteRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiteServiceImpl
    extends AbstractService<Site, Long, SiteRepository>
    implements SiteService {
    private  static final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Override
    public Site findByDomain(String domain) {
        QueryWrapper<Site> query = new QueryWrapper<>();

        query.eq("domain", domain.toLowerCase());

        Site site =  repository.selectOne(query);

        return site;
    }
}
