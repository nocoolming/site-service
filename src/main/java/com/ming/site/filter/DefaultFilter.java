package com.ming.site.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;


public class DefaultFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(DefaultFilter.class);



    @Override
    public void doFilter(jakarta.servlet.ServletRequest req, jakarta.servlet.ServletResponse res, jakarta.servlet.FilterChain filterChain) throws IOException, jakarta.servlet.ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        log.error(request.getRequestURI());

        filterChain.doFilter(req, res);
    }
}
