package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.PaymentOrder;
import com.ming.site.service.PaymentOrderService;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/paymentOrder")
public class PaymentOrderControllerV1
extends AbstractControllerV1<
        PaymentOrder,Long,
        PaymentOrderService> {
    private static final Logger log = LoggerFactory.getLogger(PaymentOrderControllerV1.class);

    @Autowired
    APIContext apiContext;
    @GetMapping("success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = new Payment();
            payment.setId(paymentId);
            PaymentExecution paymentExecute = new PaymentExecution();
            paymentExecute.setPayerId(payerId);
             payment.execute(apiContext, paymentExecute);
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
}
