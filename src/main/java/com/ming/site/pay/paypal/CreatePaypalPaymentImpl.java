package com.ming.site.pay.paypal;

import com.ming.site.config.PaypalConfig;
import com.ming.site.model.Order;
import com.ming.site.model.PaymentOrder;
import com.ming.site.pay.CreatePayment;
import com.ming.site.service.OrderService;
import com.ming.site.util.SnowflakeUtil;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@Service
public class CreatePaypalPaymentImpl
        implements CreatePayment {
    private static final Logger log = LoggerFactory.getLogger(CreatePaypalPaymentImpl.class);

    @Autowired
    PaypalConfig paypalConfig;
    @Autowired
    OrderService orderService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Payment create(Order order) {
        Amount amount = new Amount();
        amount.setCurrency(paypalConfig.getCurrency());
        amount.setTotal(order.getTotal().toString());

        Transaction transaction = new Transaction();

        transaction.setAmount(amount);
        transaction.setCustom(String.valueOf(order.getId()));
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        String cancelUrl = paypalConfig.getCancelUrl().replace("paymentId", String.valueOf(order.getId()));
        log.debug("cancel url: " + cancelUrl);
        redirectUrls.setCancelUrl(cancelUrl);

        redirectUrls.setReturnUrl(paypalConfig.getCallbackUrl());
        payment.setRedirectUrls(redirectUrls);

        order.setId(SnowflakeUtil.nextId());
        try {
            APIContext apiContext = new APIContext(
                    paypalConfig.getClientId(),
                    paypalConfig.getSecretKey(),
                    paypalConfig.getMode());
//            apiContext.setRequestId(UUID.randomUUID().toString());


            Payment createdPayment = payment.create(apiContext);

            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setChannel("paypal");
            paymentOrder.setStatus("pending");
            paymentOrder.setChannel_payment_id(createdPayment.getId());
            paymentOrder.setTotal(order.getTotal().toString());
            order.setPaymentOrder(paymentOrder);
            orderService.createOrder(order);
            log.info(createdPayment.toString());
            return createdPayment;
        } catch (PayPalRESTException e) {
            log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
