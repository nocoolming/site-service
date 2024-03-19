package com.ming.site.security;

import com.ming.site.config.JwtConfig;
import com.ming.site.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    public JwtTokenProvider(JwtConfig config) {
        this.config = config;
        this.jwtParser = Jwts.parser()
                .setSigningKey(config.getJwtSecurity());
    }

//    @Resource
    JwtConfig config;

    private final JwtParser jwtParser;

    public String generateToken(User user) {
        String username = user.getUsername();

        Date now = new Date();

        Date expireDate = new Date(now.getTime() + config.getExpirationMilliseconds());

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, config.getJwtSecurity())
                .compact();
        return token;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null
                && bearerToken.startsWith("Bearer")) {
            return bearerToken.replace("Bearer ", "");
        }

        return null;
    }

    public Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
