package com.ming.site.api.v1;

import com.ming.site.common.Result;
import com.ming.site.config.PaypalConfig;
import com.ming.site.model.Order;
import com.ming.site.pay.CreatePayment;
import com.ming.site.service.OrderService;
import com.ming.site.util.SnowflakeUtil;
import com.paypal.api.openidconnect.Session;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("site/v1/paypal")
public class PaypalCreateOrderControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(PaypalCreateOrderControllerV1.class);

    @Autowired
    PaypalConfig paypalConfig;

    @Autowired
    CreatePayment createPayment;
    @Autowired
    OrderService orderService;

    @PostMapping("createOrder")
    Result<String> createOrder(@RequestBody Order order) {
        log.info("order id: " + String.valueOf(order.getId()));

        order.setStatus("pending");

        Payment payment = createPayment.create(order);

        for (Links links : payment.getLinks()) {
            if (links.getRel().equals("approval_url")) {
                log.debug("href: " + links.getHref());
                return Result.ok(links.getHref());
            }
        }
        return Result.ok("/");
    }

    @PostMapping("createOrderReturnOrderAndLinks")
    Result<Map> createOrderReturnOrderAndLinks(
            @RequestBody Order order) {
        log.info("order id: " + String.valueOf(order.getId()));

        order.setId(SnowflakeUtil.nextId());
        order.setCreateAt(LocalDateTime.now());
        order.setUpgradeAt(LocalDateTime.now());
        order.setStatus("pending");

        Payment payment = createPayment.create(order);

        Order newOrder = orderService.findById(order.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("order", newOrder);
        map.put("links", payment.getLinks());

        return Result.ok(map);
    }
}
