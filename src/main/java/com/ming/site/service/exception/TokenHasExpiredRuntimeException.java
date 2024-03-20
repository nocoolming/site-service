package com.ming.site.service.exception;

import com.ming.site.common.jackson.BaseRuntimeException;
import com.ming.site.service.ErrorCodeEnum;

public class TokenHasExpiredRuntimeException
        extends BaseRuntimeException {
    public TokenHasExpiredRuntimeException() {
        super("Token has expired");

        this.setErrorCodeEnum(ErrorCodeEnum.TOKEN_HAS_EXPIRED);
    }
}
