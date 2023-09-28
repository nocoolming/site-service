package com.ming.site.service;

public class UserNotFoundException
        extends Exception {
    public UserNotFoundException() {
        super("User not found");
    }
}
