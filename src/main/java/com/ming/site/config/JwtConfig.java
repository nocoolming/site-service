package com.ming.site.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value(value = "${app.jwt-security}")
    private String jwtSecurity;

    @Value(value = "${app.expiration-milliseconds}")
    private long expirationMilliseconds;

    public String getJwtSecurity() {
        return jwtSecurity;
    }

    public void setJwtSecurity(String jwtSecurity) {
        this.jwtSecurity = jwtSecurity;
    }

    public long getExpirationMilliseconds() {
        return expirationMilliseconds;
    }

    public void setExpirationMilliseconds(long expirationMilliseconds) {
        this.expirationMilliseconds = expirationMilliseconds;
    }
}
