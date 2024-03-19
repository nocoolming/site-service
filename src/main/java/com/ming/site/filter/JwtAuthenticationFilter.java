package com.ming.site.filter;

import com.ming.site.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Resource
    JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {


        try {
            String accessToken = jwtTokenProvider.resolveToken(request);

            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }

            log.debug("token: " + accessToken);

//            Claims claims =

        } catch (Exception e) {
            log.error(e.getMessage());
            return;
        }


        filterChain.doFilter(request, response);
    }
}
