package com.ming.site.service;


import com.ming.site.common.BaseException;

public class NotAllOptionsHaveValuesException extends BaseException {
    public NotAllOptionsHaveValuesException() {
        super("Not all options have values!");
    }
}
