package com.ming.site.pay.paypal;

import com.ming.site.config.PaypalConfig;
import com.ming.site.model.Order;
import com.ming.site.pay.CreatePayment;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatePaypalPayment
    implements CreatePayment {
    private static final Logger log = LoggerFactory.getLogger(CreatePaypalPayment.class);

    @Autowired
    PaypalConfig paypalConfig;
    @Override
    public Payment create(Order order) {
        Amount amount = new Amount();
        amount.setCurrency(paypalConfig.getCurrency());
        amount.setTotal(order.getOrderTotal().toString());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
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
        try{
            APIContext apiContext = new APIContext(paypalConfig.getClientId(), paypalConfig.getSecretKey(), paypalConfig.getMode());
            Payment createdPayment = payment.create(apiContext);

            System.out.println(createdPayment.toString());
            return createdPayment;
        } catch (PayPalRESTException e) {
            log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
