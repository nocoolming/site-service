package com.ming.site.pay;

import com.ming.site.model.Order;

public interface CreatePayment {
    String create(Order order);
}
