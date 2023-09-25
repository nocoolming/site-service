package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.PaymentOrder;
import com.ming.site.service.PaymentOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/paymentOrder")
public class PaymentOrderControllerV1
extends AbstractControllerV1<
        PaymentOrder,Long,
        PaymentOrderService> {
}
