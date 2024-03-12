package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Domain;
import com.ming.site.model.Site;
import com.ming.site.service.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("site/v1/domain")

public class DomainControllerV1
        extends AbstractControllerV1<
        Domain,
        Long,
        DomainService> {
    private static final Logger log = LoggerFactory.getLogger(DomainControllerV1.class);

    @GetMapping("domain/{domain}")
    public Result<Site> getSiteByDomain(@PathVariable String domain) {
        Site site = service.findSiteByDomain(domain);

        return Result.ok(site);
    }

    @GetMapping("siteId/{siteId}")
    public Result<List<Domain>> getDomainsBySiteId(@PathVariable long siteId) {
        List<Domain> domains = service.findBySiteId(siteId);

        return Result.ok(domains);
    }

}
