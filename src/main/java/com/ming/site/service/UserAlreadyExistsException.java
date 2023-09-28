package com.ming.site.service;

public class UserAlreadyExistsException
extends Exception{
    public UserAlreadyExistsException(){
        super("用户已经存在");
    }
}
