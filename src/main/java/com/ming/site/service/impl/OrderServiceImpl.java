package com.ming.site.service.impl;

import com.ming.site.model.Order;
import com.ming.site.repository.OrderRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl
        extends AbstractService<Order, Long, OrderRepository>
        implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
}
