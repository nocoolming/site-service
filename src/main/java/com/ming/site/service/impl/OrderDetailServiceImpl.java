package com.ming.site.service.impl;

import com.ming.site.mapper.OrderDetailMapper;
import com.ming.site.model.OrderDetail;
import com.ming.site.service.AbstractService;
import com.ming.site.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl
        extends AbstractService<OrderDetail, Long, OrderDetailMapper>
        implements OrderDetailService {
    private static final Logger log = LoggerFactory.getLogger(OrderDetailServiceImpl.class);
}
