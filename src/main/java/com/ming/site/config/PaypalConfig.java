package com.ming.site.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {
    private static final Logger log = LoggerFactory.getLogger(PaypalConfig.class);

    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.secretKey}")
    private String secretKey;

    @Value("${paypal.cancelUrl}")
    private String cancelUrl;


    @Value("${paypal.callbackUrl}")
    private String callbackUrl;


    @Value("${paypal.currency}")
    private String currency;

    @Value("${paypal.mode}")
    private String mode;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
