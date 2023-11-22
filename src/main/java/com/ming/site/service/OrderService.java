package com.ming.site.service;

import com.ming.site.model.CartItem;
import com.ming.site.model.Order;
import com.ming.site.service.model.CreateOrderModel;

import java.util.List;


public interface OrderService
        extends CrudService<Order, Long> {

    Order createOrderByCartId(long cartId);

    Order createOrder(Order order);


    Order createOrder(long cartId);

    Order createOrder(CreateOrderModel model);

    Order approve(long orderId);

    Order cancel(long orderId);
}
