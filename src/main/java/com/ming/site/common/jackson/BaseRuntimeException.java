package com.ming.site.common.jackson;

import com.ming.site.service.ErrorCodeEnum;

public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String msg) {
        super(msg);

    }

    private ErrorCodeEnum errorCodeEnum;

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}

