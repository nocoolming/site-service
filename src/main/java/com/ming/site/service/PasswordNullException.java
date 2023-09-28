package com.ming.site.service;

public class PasswordNullException  extends Exception {
    public PasswordNullException(){
        super("Password is null!");
    }
}
