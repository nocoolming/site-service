package com.ming.site.api.v1;

import com.ming.site.common.Result;
import com.ming.site.model.PaymentOrder;
import com.ming.site.service.PaymentOrderService;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CallbackPaymentControllerV1 {
    private static final Logger log
            = LoggerFactory.getLogger(CallbackPaymentControllerV1.class);

    @Autowired
    APIContext apiContext;

    @Autowired
    PaymentOrderService paymentOrderService;

    @GetMapping("site/v1/callback")
    Result<PaymentOrder> callback(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId)
            throws PayPalRESTException {
        PaymentOrder paymentOrder
                = paymentOrderService.callback(paymentId, payerId);

        return Result.ok(paymentOrder);
    }

}
