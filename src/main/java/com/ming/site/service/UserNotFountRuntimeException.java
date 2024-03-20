package com.ming.site.service;

import com.ming.site.common.jackson.BaseRuntimeException;

public class UserNotFountRuntimeException
        extends BaseRuntimeException {
    public UserNotFountRuntimeException() {
        super("User not found");

        this.setErrorCodeEnum(ErrorCodeEnum.USER_NOT_EXIST);
    }
}
