package com.ming.site.pay;

import com.ming.site.model.Order;
import com.paypal.api.payments.Payment;

public interface CreatePayment {
    Payment create(Order order);
}
