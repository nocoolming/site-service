package com.ming.site.api.v1;

import com.ming.site.common.Result;
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

    @GetMapping("site/v1/callback")
    Result<String> callback(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId,
            HttpServletRequest request){
        log.debug("paymentId: " + String.valueOf(paymentId));
        log.info(request.getRequestURI());
        try {
            Payment payment = new Payment();
            payment.setId(paymentId);
            PaymentExecution paymentExecute = new PaymentExecution();
            paymentExecute.setPayerId(payerId);
            payment.execute(apiContext, paymentExecute);
            if (payment.getState().equals("approved")) {
                return Result.ok("success");
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }

        return Result.ok("done");
    }

}
