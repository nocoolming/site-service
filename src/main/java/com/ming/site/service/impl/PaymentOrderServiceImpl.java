package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.common.Result;
import com.ming.site.model.PaymentOrder;
import com.ming.site.repository.PaymentOrderRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.PaymentOrderService;
import com.ming.site.util.SnowflakeUtil;
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

@Service
public class PaymentOrderServiceImpl
        extends AbstractService<PaymentOrder, Long, PaymentOrderRepository>
        implements PaymentOrderService {
    private static final Logger log = LoggerFactory.getLogger(PaymentOrderServiceImpl.class);
    @Autowired
    APIContext apiContext;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = PayPalRESTException.class)
    public PaymentOrder create(String paymentId, String payerId) {
        try {
            Payment payment = new Payment();
            payment.setId(paymentId);
            PaymentExecution paymentExecute = new PaymentExecution();
            paymentExecute.setPayerId(payerId);
            payment = payment.execute(apiContext, paymentExecute);
            if (payment.getState().equals("approved")) {

                PaymentOrder paymentOrder = new PaymentOrder();
                paymentOrder.setId(SnowflakeUtil.nextId());
                paymentOrder.setChannel("paypal");
//                paymentOrder.setOrderId(paymentId);
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
            payment = payment.execute(apiContext, paymentExecute);
            if (payment.getState().equals("approved")) {

                Transaction transaction = payment.getTransactions().get(0);
                String orderIdString = transaction.getCustom();
                long id = Long.parseLong(orderIdString);
                PaymentOrder paymentOrder = this.findById(id);

                if(paymentOrder == null){
                    throw new RuntimeException("not found payment id");
                }
                paymentOrder.setChannel_payment_id(paymentId);
                paymentOrder.setPayerId(payerId);
                paymentOrder.setUpgradeAt(LocalDateTime.now());
                paymentOrder.setStatus("success");
                paymentOrder.setTotal(transaction.getAmount().getTotal());

                this.update(paymentOrder);
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
        return paymentOrder;
    }
}
