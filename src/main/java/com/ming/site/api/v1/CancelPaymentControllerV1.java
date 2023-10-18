package com.ming.site.api.v1;

import com.ming.site.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CancelPaymentControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(CancelPaymentControllerV1.class);

    @GetMapping("site/v1/cancel/{orderId}")
    Result<String> cancel(@PathVariable long orderId){
        log.debug("orderId: " + String.valueOf(orderId));
        return Result.ok("done");
    }
}
