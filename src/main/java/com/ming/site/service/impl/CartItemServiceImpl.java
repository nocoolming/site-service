package com.ming.site.service.impl;

import com.ming.site.model.CartItem;
import com.ming.site.repository.CartItemRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl
        extends AbstractService<CartItem, Long, CartItemRepository>
        implements CartItemService {
}
