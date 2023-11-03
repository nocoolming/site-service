package com.ming.site.service;

import com.ming.site.model.PaymentOrder;
import com.paypal.base.rest.PayPalRESTException;

public interface PaymentOrderService
        extends CrudService<PaymentOrder, Long> {
    PaymentOrder create(String paymentId, String payerId);

    PaymentOrder callback(String paymentId, String payerId) throws PayPalRESTException;

    PaymentOrder getPaymentOrderByChannelPaymentId(String paymentId);
}
