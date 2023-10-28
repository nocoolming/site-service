package com.ming.site.service;

import com.ming.site.model.Cart;
import com.ming.site.util.SnowflakeUtil;

import java.time.LocalDateTime;

public interface CartService
        extends CrudService<Cart, Long> {


    Cart createCart(Cart cart);
}
