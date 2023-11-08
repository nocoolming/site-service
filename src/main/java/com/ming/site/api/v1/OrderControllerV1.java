package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Order;
import com.ming.site.service.OrderService;
import com.ming.site.service.ProductImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("createOrder")
    Result<Order> createOrder(@RequestBody Order order){

        Order newOrder = service.createOrder(order);
        log.debug(newOrder.toString());

        return Result.ok(newOrder);
    }

    @PostMapping("createOrderByCartId")
    Result<Order> createOrderByCartId(@RequestBody long id){
        Order order = service.createOrderByCartId(id);

        return Result.ok(order);
    }

    @PostMapping("approve")
    Result<Order> approve(@RequestBody long orderId){
        Order order = service.approve(orderId);

        return Result.ok(order);
    }

    @PostMapping("cancel")
    Result<Order> cancel(@RequestBody long orderId){
        Order order = service.cancel(orderId);

        return Result.ok(order);
    }

}
