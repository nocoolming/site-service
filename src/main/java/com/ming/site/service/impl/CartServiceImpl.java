package com.ming.site.service.impl;

import com.ming.site.model.Cart;
import com.ming.site.model.CartItem;
import com.ming.site.repository.CartRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CartItemService;
import com.ming.site.service.CartService;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CartServiceImpl
        extends AbstractService<Cart, Long, CartRepository>
        implements CartService {
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    CartItemService cartItemService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Cart createCart(Cart cart) {
        cart.setId(SnowflakeUtil.nextId());
        cart.setCreateAt(LocalDateTime.now());
        cart.setUpgradeAt(LocalDateTime.now());

        this.repository.insert(cart);

        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.setId(SnowflakeUtil.nextId());
            cartItem.setCartId(cart.getId());
            cartItem.setCreateAt(LocalDateTime.now());
            cartItem.setUpgradeAt(LocalDateTime.now());

            BigDecimal subtotalItem =
                    cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
            subtotal = subtotal.add(subtotalItem);

            cartItemService.insert(cartItem);
        }

        cart.setSubtotal(subtotal);

        this.update(cart);

        return cart;
    }
}
