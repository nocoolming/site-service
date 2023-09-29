package com.ming.site.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private final static Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler
    public Result errorHandler(Exception e){
        Result result = Result.error(e.getMessage());

        log.error(e.getMessage());

        return result;
    }
}
