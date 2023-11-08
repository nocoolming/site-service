package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.model.Site;
import com.ming.site.repository.SiteRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.SiteService;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Site insert(Site site){
        if(site.getId()<= 0){
            site.setId(SnowflakeUtil.nextId());
        }
        site.setCreateAt(LocalDateTime.now());
        site.setUpgradeAt(LocalDateTime.now());

        repository.insert(site);
        return site;
    }
}
