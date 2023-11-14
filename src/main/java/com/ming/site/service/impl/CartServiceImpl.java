package com.ming.site.service.impl;

import com.ming.site.mapper.CartMapper;
import com.ming.site.model.Cart;
import com.ming.site.model.CartItem;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CartItemService;
import com.ming.site.service.CartService;
import com.ming.site.util.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl extends AbstractService<Cart, Long, CartMapper> implements CartService {

    @Autowired
    CartItemService cartItemService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cart insert(Cart o) {
        if (o.getId() <= 0) {
            o.setId(SnowflakeUtil.nextId());
        }
        o.setCreateAt(LocalDateTime.now());
        o.setUpgradeAt(LocalDateTime.now());
        this.mapper.insertSelective(o);

        return o;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int update(Cart cart) {
        Cart old = this.findById(cart.getId());

        cart.setCreateAt(old.getCreateAt());
        cart.setUpgradeAt(LocalDateTime.now());
        return mapper.update(cart);
    }

    @Override
    public Cart findById(Long id) {
        Cart cart = mapper.selectOneById(id);

        return cart;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Cart createCart(Cart cart) {
        cart.setId(SnowflakeUtil.nextId());
        cart.setCreateAt(LocalDateTime.now());
        cart.setUpgradeAt(LocalDateTime.now());

        this.mapper.insert(cart);

        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.setId(SnowflakeUtil.nextId());
            cartItem.setCartId(cart.getId());
            cartItem.setCreateAt(LocalDateTime.now());
            cartItem.setUpgradeAt(LocalDateTime.now());

            BigDecimal subtotalItem = cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
            subtotal = subtotal.add(subtotalItem);

            cartItemService.insert(cartItem);
        }

        cart.setSubtotal(subtotal);

        this.update(cart);

        return cart;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Cart getCartWithRelationship(Long id) {
        Cart cart = this.findById(id);
        List<CartItem> items = cartItemService.getItemsByCartId(id);

        cart.setCartItems(items);
        return cart;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void clean(Long id) {
        Cart cart = this.getCartWithRelationship(id);

        cart.setSubtotal(BigDecimal.ZERO);
        cart.setCurrency("");
        cart.setUpgradeAt(LocalDateTime.now());

        this.update(cart);

        for (CartItem item : cart.getCartItems()) {
            cartItemService.deleteById(item.getId());
        }
    }
}
