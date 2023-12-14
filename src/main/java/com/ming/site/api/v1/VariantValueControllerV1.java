package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.VariantValue;
import com.ming.site.service.VariantValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/variantValue")
public class VariantValueControllerV1
        extends AbstractControllerV1<
        VariantValue,
        Long,
        VariantValueService> {
    private static final Logger log = LoggerFactory.getLogger(VariantValueControllerV1.class);
}
