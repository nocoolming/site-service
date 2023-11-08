package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.common.Result;
import com.ming.site.config.PaypalConfig;
import com.ming.site.model.Order;
import com.ming.site.model.PaymentOrder;
import com.ming.site.repository.PaymentOrderRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.OrderService;
import com.ming.site.service.PaymentOrderService;
import com.ming.site.util.SnowflakeUtil;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentOrderServiceImpl
        extends AbstractService<PaymentOrder, Long, PaymentOrderRepository>
        implements PaymentOrderService {
    private static final Logger log = LoggerFactory.getLogger(PaymentOrderServiceImpl.class);

    @Autowired
    PaypalConfig paypalConfig;
    @Autowired
    OrderService orderService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = PayPalRESTException.class)
    public PaymentOrder create(String paymentId, String payerId) {
        try {
            Payment payment = new Payment();
            payment.setId(paymentId);
            PaymentExecution paymentExecute = new PaymentExecution();
            paymentExecute.setPayerId(payerId);
            APIContext apiContext = new APIContext(
                    paypalConfig.getClientId(),
                    paypalConfig.getSecretKey(),
                    paypalConfig.getMode());
//            apiContext.setRequestId(UUID.randomUUID().toString());
            payment = payment.execute(apiContext, paymentExecute);
            if (payment.getState().equals("approved")) {

                PaymentOrder paymentOrder = new PaymentOrder();
                paymentOrder.setId(SnowflakeUtil.nextId());
                paymentOrder.setChannel("paypal");
                paymentOrder.setCreateAt(LocalDateTime.now());
                repository.insert(paymentOrder);
                return paymentOrder;
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PaymentOrder callback(String paymentId, String payerId) throws PayPalRESTException {
        try {
            Payment payment = new Payment();
            payment.setId(paymentId);
            PaymentExecution paymentExecute = new PaymentExecution();
            paymentExecute.setPayerId(payerId);
            APIContext apiContext = new APIContext(
                    paypalConfig.getClientId(),
                    paypalConfig.getSecretKey(),
                    paypalConfig.getMode());
//            apiContext.setRequestId(UUID.randomUUID().toString());
            payment = payment.execute(apiContext, paymentExecute);
            if (payment.getState().equals("approved")) {

                PaymentOrder paymentOrder = this.getPaymentOrderByChannelPaymentId(paymentId);

                if (paymentOrder == null) {
                    throw new RuntimeException("not found payment id");
                }
                PayerInfo payerInfo = payment.getPayer().getPayerInfo();
                paymentOrder.setPayerId(payerInfo.getPayerId());
                paymentOrder.setPayerEmail(payerInfo.getEmail());
                paymentOrder.setPayerFirstName(payerInfo.getFirstName());
                paymentOrder.setPayerLastName(payerInfo.getLastName());
                paymentOrder.setUpgradeAt(LocalDateTime.now());
                paymentOrder.setStatus(payment.getState());

                this.update(paymentOrder);

                Order order = orderService.findById(paymentOrder.getId());
                order.setStatus("paid");
                order.setUpgradeAt(LocalDateTime.now());
                orderService.update(order);

                return paymentOrder;
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
            throw e;
        }

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PaymentOrder getPaymentOrderByChannelPaymentId(String paymentId) {
        QueryWrapper<PaymentOrder> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .eq("channel_payment_id", paymentId);

        PaymentOrder paymentOrder = repository.selectOne(queryWrapper);

        Order order = orderService.findById(paymentOrder.getId());
        paymentOrder.setOrder(order);
        return paymentOrder;
    }
}
