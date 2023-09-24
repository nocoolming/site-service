package com.ming.site.service.impl;

import com.ming.site.model.PaymentOrder;
import com.ming.site.repository.PaymentOrderRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.PaymentOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentOrderServiceImpl
    extends AbstractService<PaymentOrder, Long, PaymentOrderRepository>
    implements PaymentOrderService {
    private static final Logger log = LoggerFactory.getLogger(PaymentOrderServiceImpl.class);
}
