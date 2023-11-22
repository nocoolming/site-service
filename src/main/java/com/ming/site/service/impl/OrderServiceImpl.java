package com.ming.site.service.impl;

import com.ming.site.mapper.OrderMapper;
import com.ming.site.model.*;
import com.ming.site.service.*;
import com.ming.site.service.model.CreateOrderModel;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl
        extends AbstractService<Order, Long, OrderMapper>
        implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    PaymentOrderService paymentOrderService;
    @Autowired
    UserService userService;

    void validCreateOrderByCartId(long cartId) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Order insert(Order order) {
        if (order.getId() == null || order.getId() <= 0) {
            order.setId(SnowflakeUtil.nextId());
        }
        order.setCreateAt(LocalDateTime.now());
        order.setUpgradeAt(LocalDateTime.now());

        this.mapper.insert(order);

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

        this.mapper.insert(order);

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
        order.setId(SnowflakeUtil.nextId());
        order = this.insert(order);

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId(order.getId());
        paymentOrder.setCreateAt(LocalDateTime.now());
        paymentOrder.setUpgradeAt(LocalDateTime.now());

        paymentOrderService.insert(paymentOrder);
        order.setPaymentOrder(paymentOrder);
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Order createOrder(long cartId) {
        Cart cart = cartService.getCartWithRelationship(cartId);
        Order order = new Order();
        order.setId(SnowflakeUtil.nextId());
        BigDecimal subtotal = BigDecimal.ZERO;

        List<OrderDetail> orderDetails =
                cart.getCartItems()
                        .stream()
                        .map(item -> {
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setId(SnowflakeUtil.nextId());
                            orderDetail.setCreateAt(LocalDateTime.now());
                            orderDetail.setUpgradeAt(LocalDateTime.now());
                            orderDetail.setPrice(item.getPrice());
                            orderDetail.setSubtotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                            orderDetail.setQuantity(item.getQuantity());
                            orderDetail.setOrderId(order.getId());

                            orderDetailService.insert(orderDetail);

                            subtotal.add(orderDetail.getSubtotal());

                            return orderDetail;
                        }).collect(Collectors.toList());

        order.setTotal(subtotal);

        this.insert(order);
        return order;
    }

    @Override
    public Order createOrder(CreateOrderModel model) {
        List<CartItem> cartItemList = cartItemService.getItemsByIds(model.getCartItems());
        Order order = new Order();
        order.setId(SnowflakeUtil.nextId());

        BigDecimal subTotal = BigDecimal.ZERO;
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (var cartItem : cartItemList) {
            BigDecimal price = cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();

            BigDecimal total = price.multiply(new BigDecimal(quantity));
            subTotal = subTotal.add(total);

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setSubtotal(subTotal);
            orderDetail.setOrderId(order.getId());
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(price);
            orderDetail.setSubtotal(total);
            orderDetail.setCreateUserId(model.getUserId());
            orderDetail.setTitle(cartItem.getProduct().getTitle());

            orderDetails.add(orderDetail);

            // this.insert 已经处理了order and orderDetails id createAt upgradeAt and id
            // 这里不需要再去做重复的操作
        }
        order.setTotal(subTotal);
        order.setOrderDetails(orderDetails);
        order.setState(model.getState());
        order.setAddress(model.getAddress());
        order.setCity(model.getCity());
        order.setCountry(model.getCountry());
        order.setFirstName(model.getFirstName());
        order.setLastName(model.getLastName());
        order.setPhone(model.getPhone());
        order.setZip(model.getZip());
        order.setCreateUserId(model.getUserId());
        order.setEmail(model.getEmail());

        this.insert(order);
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Order approve(long orderId) {
        Order order = this.findById(orderId);

        order.setStatus("success");
        order.setUpgradeAt(LocalDateTime.now());

        this.update(order);
        return order;
    }

    @Override
    public Order cancel(long orderId) {
        Order order = this.findById(orderId);

        order.setStatus("cancel");
        order.setUpgradeAt(LocalDateTime.now());

        this.update(order);
        return order;
    }
}
