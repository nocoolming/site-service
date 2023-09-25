package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Site;
import com.ming.site.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/site")
public class SiteControllerV1
        extends AbstractControllerV1<
        Site,
        Long,
        SiteService> {
    private static final Logger log = LoggerFactory.getLogger(SiteControllerV1.class);

}
