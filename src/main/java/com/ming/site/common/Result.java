package com.ming.site.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Result<R> {
    private static final Logger log = LoggerFactory.getLogger(Result.class);

    public Result(R r) {
        this.setResult(r);
        this.setCreateAt(LocalDateTime.now());
        this.setMode("success");
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
        result.setMode("error");
        return result;
    }

    private R result;
    private LocalDateTime createAt;
    private String mode;
    private String errorMessage;

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
