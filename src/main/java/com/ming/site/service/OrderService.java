package com.ming.site.service;

import com.ming.site.model.Order;


public interface OrderService
        extends CrudService<Order, Long> {

    Order createOrderByCartId(long cartId);

    Order createOrder(Order order);

    Order approve(long orderId);

    Order cancel(long orderId);
}
