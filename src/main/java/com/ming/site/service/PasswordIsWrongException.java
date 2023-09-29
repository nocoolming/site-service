package com.ming.site.service;

public class PasswordIsWrongException extends Exception {
    public PasswordIsWrongException() {
        super("Password wrong!");
    }
}
