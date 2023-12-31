package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Order;
import com.ming.site.service.OrderService;
import com.ming.site.service.ProductImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/order")
public class OrderControllerV1
extends AbstractControllerV1<
        Order,
        Long,
        OrderService> {
    private static final Logger log = LoggerFactory.getLogger(ProductImageService.class);
}
