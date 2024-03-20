package com.ming.site.jwt.model;

import com.ming.site.jwt.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken
        implements AuthenticationToken {
    private String username;
    private String token;

    public JwtToken(String token) {
        this.token = token;

        JwtUtil jwtUtil = new JwtUtil();

        this.username = jwtUtil.getClaimFiled(token, "username");
    }


    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
