package com.ming.site.api.v1;

import com.ming.site.common.Result;
import com.ming.site.config.PaypalConfig;
import com.ming.site.model.Order;
import com.ming.site.pay.CreatePayment;
import com.ming.site.util.SnowflakeUtil;
import com.paypal.api.openidconnect.Session;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        log.info("order id: " + String.valueOf(orderId));
        Order order = new Order();
        order.setId(SnowflakeUtil.nextId());
        order.setCreateAt(LocalDateTime.now());
        order.setUpgradeAt(LocalDateTime.now());
        order.setOrderTotal(BigDecimal.valueOf(88.8));
        Payment payment = createPayment.create(order);

        for (Links links : payment.getLinks()) {
            if (links.getRel().equals("approval_url")) {
                log.debug("href: " + links.getHref());
                return Result.ok(links.getHref()) ;
            }
        }
        return Result.ok("/");
    }

    @GetMapping("getUsersConsent")
    Result<String> getUsersConsent(){
        APIContext apiContext = new APIContext(
                paypalConfig.getClientId(),
                paypalConfig.getSecretKey(),
                paypalConfig.getMode()
        );
        List<String> scopes = new ArrayList<String>() {{
            /**
             * 'openid'
             * 'profile'
             * 'address'
             * 'email'
             * 'phone'
             * 'https://uri.paypal.com/services/paypalattributes'
             * 'https://uri.paypal.com/services/expresscheckout'
             * 'https://uri.paypal.com/services/invoicing'
             */
            add("openid");
            add("profile");
            add("email");
        }};
        String redirectUrl = Session.getRedirectURL("UserConsent", scopes, apiContext);
        log.info(redirectUrl);

        return Result.ok(redirectUrl);
    }
}
