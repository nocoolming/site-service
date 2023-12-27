package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Value;
import com.ming.site.service.ValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/value")
public class ValueControllerV1
        extends AbstractControllerV1<
        Value,
        Long,
        ValueService> {
    private static final Logger log = LoggerFactory.getLogger(ValueControllerV1.class);


}
