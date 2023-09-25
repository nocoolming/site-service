package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.OrderDetail;
import com.ming.site.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/orderDetail")
public class OrderDetailControllerV1
extends AbstractControllerV1<
        OrderDetail, Long,
        OrderDetailService> {
    private static final Logger log = LoggerFactory.getLogger(OrderDetailControllerV1.class);
}
