package com.ming.site.api.v1;

import com.ming.site.common.Result;
import com.paypal.base.rest.APIContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CallbackPaymentControllerV1 {
    private static final Logger log
            = LoggerFactory.getLogger(CallbackPaymentControllerV1.class);

    @Autowired
    APIContext apiContext;

    @GetMapping("site/v1/callback/{orderId}")
    Result<String> callback(@PathVariable long orderId){
        log.debug("orderId: " + String.valueOf(orderId));


        return Result.ok("done");
    }

}
