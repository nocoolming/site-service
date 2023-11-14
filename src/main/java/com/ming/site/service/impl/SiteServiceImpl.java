package com.ming.site.service.impl;


import com.ming.site.mapper.SiteMapper;
import com.ming.site.model.Site;
import com.ming.site.service.AbstractService;
import com.ming.site.service.SiteService;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SiteServiceImpl
        extends AbstractService<Site, Long, SiteMapper>
        implements SiteService {
    private  static final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Override
    public Site findByDomain(String domain) {
        QueryWrapper query = QueryWrapper.create()
                .select()
                .where("domain=?", domain.toLowerCase());

        Site site =  mapper.selectOneByQuery(query);

        return site;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Site insert(Site site){
        if(site.getId()<= 0){
            site.setId(System.currentTimeMillis());
        }
        site.setCreateAt(LocalDateTime.now());
        site.setUpgradeAt(LocalDateTime.now());

        mapper.insert(site);
        return site;
    }
}
