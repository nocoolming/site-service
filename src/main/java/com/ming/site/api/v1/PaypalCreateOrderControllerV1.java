package com.ming.site.api.v1;

import com.ming.site.common.Result;
import com.ming.site.config.PaypalConfig;
import com.ming.site.model.Order;
import com.ming.site.pay.CreatePayment;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("site/v1/paypal")
public class PaypalCreateOrderControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(PaypalCreateOrderControllerV1.class);

    @Autowired
    PaypalConfig paypalConfig;

    @Autowired
    CreatePayment createPayment;

    @PostMapping("createOrder")
    Result<String> createOrder(@RequestBody long orderId){
//        PayPalHttpClient client = new PayPalHttpClient(
//                new SandboxEnvironment("YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET")
        log.error("order id: " + String.valueOf(orderId));
        Order order = new Order();
        order.setId(SnowflakeUtil.nextId());
        order.setCreateAt(LocalDateTime.now());
        order.setUpgradeAt(LocalDateTime.now());
        order.setOrderTotal(BigDecimal.valueOf(88.8));
        String result = createPayment.create(order);


        return Result.ok(result);
    }
}
