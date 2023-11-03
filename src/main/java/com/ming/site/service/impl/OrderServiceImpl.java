package com.ming.site.service.impl;

import com.ming.site.model.*;
import com.ming.site.repository.OrderRepository;
import com.ming.site.service.*;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl
        extends AbstractService<Order, Long, OrderRepository>
        implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    CartService cartService;

    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    PaymentOrderService paymentOrderService;

    void validCreateOrderByCartId(long cartId) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Order insert(Order order) {
        order.setId(SnowflakeUtil.nextId());
        order.setCreateAt(LocalDateTime.now());
        order.setUpgradeAt(LocalDateTime.now());

        repository.insert(order);

        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setId(SnowflakeUtil.nextId());
            if (order.getCreateUserId() != null && order.getCreateUserId() > 0) {
                orderDetail.setCreateUserId(order.getCreateUserId());
            }
            orderDetail.setUpgradeAt(LocalDateTime.now());
            orderDetail.setCreateAt(LocalDateTime.now());
            orderDetail.setOrderId(order.getId());

            orderDetailService.insert(orderDetail);
        }

        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order createOrderByCartId(long cartId) {
        this.validCreateOrderByCartId(cartId);
        Cart cart = cartService.getCartWithRelationship(cartId);

        Order order = new Order();
        order.setId(SnowflakeUtil.nextId());
        order.setCreateAt(LocalDateTime.now());
        order.setUpgradeAt(LocalDateTime.now());
        order.setSiteId(cart.getSiteId());

        repository.insert(order);

        List<OrderDetail> list = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setTitle(product.getTitle());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setId(SnowflakeUtil.nextId());
            orderDetail.setOrderId(order.getId());
            orderDetail.setCreateAt(LocalDateTime.now());
            orderDetail.setUpgradeAt(LocalDateTime.now());

            orderDetailService.insert(orderDetail);

            list.add(orderDetail);
        }

        order.setOrderDetails(list);

        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Order createOrder(Order order) {
        if (order.getId() <= 0) {
            order.setId(SnowflakeUtil.nextId());
        }
        order.setCreateAt(LocalDateTime.now());
        order.setUpgradeAt(LocalDateTime.now());

        repository.insert(order);

        PaymentOrder paymentOrder = order.getPaymentOrder();
        paymentOrder.setId(order.getId());
        paymentOrder.setCreateAt(LocalDateTime.now());
        paymentOrder.setUpgradeAt(LocalDateTime.now());

        paymentOrderService.insert(paymentOrder);
        return order;
    }
}
