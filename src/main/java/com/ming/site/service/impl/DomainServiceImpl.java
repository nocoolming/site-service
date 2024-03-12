package com.ming.site.service.impl;

import com.ming.site.mapper.DomainMapper;
import com.ming.site.model.Domain;
import com.ming.site.model.Site;
import com.ming.site.service.AbstractService;
import com.ming.site.service.DomainService;
import com.ming.site.service.SiteService;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainServiceImpl
        extends AbstractService<Domain, Long, DomainMapper>
        implements DomainService {
    private static final Logger log = LoggerFactory.getLogger(DomainServiceImpl.class);

    @Resource
    SiteService siteService;

    @Override
    public List<Domain> findBySiteId(long siteId) {
        List<Domain> domains = null;

        domains = this.mapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("site_id", siteId)
        );


        return domains;
    }

    @Override
    public Site findSiteByDomain(String domain) {
        Domain d = this.mapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("domain", domain)
        );

        if (d != null) {
            return siteService.findById(d.getSiteId());
        }

        return null;
    }
}
