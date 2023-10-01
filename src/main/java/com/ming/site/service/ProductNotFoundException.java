package com.ming.site.service;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(){
        super("Product not found.");
    }
}
