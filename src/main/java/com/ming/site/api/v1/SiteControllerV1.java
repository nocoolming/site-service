package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Category;
import com.ming.site.model.Site;
import com.ming.site.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("site/v1/site")
public class SiteControllerV1
        extends AbstractControllerV1<
        Site,
        Long,
        SiteService> {
    private static final Logger log = LoggerFactory.getLogger(SiteControllerV1.class);

    @GetMapping("domain/{domain}")
    Result<Site> findByDomain(@PathVariable String domain){

        if(domain.startsWith("www.")){
            domain = domain.substring(4);
        }
        log.error("domain:{}",domain);
        Site site = service.findByDomain(domain);


        return Result.ok(site);
    }
}
