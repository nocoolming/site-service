package com.ming.site.service;

import com.ming.site.model.CartItem;
import com.ming.site.model.Order;

import java.util.List;


public interface OrderService
        extends CrudService<Order, Long> {

    Order createOrderByCartId(long cartId);

    Order createOrder(Order order);


    Order createOrder(long cartId);

    Order approve(long orderId);

    Order cancel(long orderId);
}
