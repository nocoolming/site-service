package com.ming.site.service.impl;

import com.ming.site.model.Cart;
import com.ming.site.repository.CartRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public  class CartServiceImpl
        extends AbstractService<Cart, Long,  CartRepository>
        implements CartService {
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

}
