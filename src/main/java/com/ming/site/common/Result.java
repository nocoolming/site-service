package com.ming.site.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Result<R> {
    private static final Logger log = LoggerFactory.getLogger(Result.class);

    public Result(R r) {
        this.setValue(r);
        this.setCreateAt(LocalDateTime.now());
        this.setStatus("success");
    }

    public Result() {
        this.setCreateAt(LocalDateTime.now());
    }

    public static <R> Result<R> success(R r) {
        return new Result<R>(r);
    }

    public static <R> Result<R> ok(R r) {
        return success(r);
    }

    public static Result error(String errorMessage) {
        Result result = new Result();
        result.setErrorMessage(errorMessage);
        result.setStatus("error");
        return result;
    }

    private R value;
    private LocalDateTime createAt;
    private String status;
    private String errorMessage;

    public R getValue() {
        return value;
    }

    public void setValue(R value) {
        this.value = value;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
