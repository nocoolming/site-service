package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Stock;
import com.ming.site.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/stock")
public class StockControllerV1
        extends AbstractControllerV1<
        Stock,
        Long,
        StockService> {
    private static final Logger log = LoggerFactory.getLogger(StockControllerV1.class);
}
