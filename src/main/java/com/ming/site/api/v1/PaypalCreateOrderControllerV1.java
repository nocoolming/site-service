package com.ming.site.api.v1;

import com.ming.site.common.Result;
import com.ming.site.config.PaypalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/paypal")
public class PaypalCreateOrderControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(PaypalCreateOrderControllerV1.class);

    @Autowired
    PaypalConfig paypalConfig;

    @PostMapping("createOrder")
    Result<String> createOrder(long orderId){
//        PayPalHttpClient client = new PayPalHttpClient(
//                new SandboxEnvironment("YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET")
        log.error("order id: " + String.valueOf(orderId));
        return Result.ok("success");
    }
}
