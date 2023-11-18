package com.ming.site.service.impl;


import com.ming.site.mapper.CartItemMapper;
import com.ming.site.model.Cart;
import com.ming.site.model.CartItem;
import com.ming.site.model.Product;
import com.ming.site.model.User;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CartItemService;
import com.ming.site.service.CartService;
import com.ming.site.service.ProductService;
import com.ming.site.service.model.AddToCartModel;
import com.ming.site.util.SnowflakeUtil;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartItemServiceImpl
        extends AbstractService<CartItem, Long, CartItemMapper>
        implements CartItemService {

    private static final Logger log
            = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CartItem insert(CartItem o){
        o.setId(SnowflakeUtil.nextId());
        o.setCreateAt(LocalDateTime.now());
        o.setUpgradeAt(LocalDateTime.now());
        this.mapper.insertSelective(o);

        return o;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CartItem addToCart(AddToCartModel model) {
        Cart cart = cartService.getCartWithRelationship(model.getCartId());
        CartItem cartItem = new CartItem();

        cartItem.setCartId( model.getCartId());
        cartItem.setId(SnowflakeUtil.nextId());
        cartItem.setUpgradeAt(LocalDateTime.now());
        cartItem.setCreateAt(LocalDateTime.now());
        cartItem.setCurrency(model.getCurrency());

        this.insert(cartItem);
        cart.getCartItems().add(cartItem);

        return cartItem;
    }

    @Override
    public List<CartItem> getItemsByCartId(long id) {
//        QueryWrapper query = new QueryWrapper();
//
//        query.eq("cart_id", id)
//                .orderByDesc("upgrade_at");
//        List<CartItem> list =  this.mapper.selectList(query);
//        for (CartItem cartItem : list) {
//            Product product = productService.findById(cartItem.getProductId());
//            cartItem.setProduct(product);
//        }
//        return list;

        QueryWrapper query = QueryWrapper.create()
                .eq("cart_id", id)
                .orderBy("upgrade_at desc");

        return mapper.selectListWithRelationsByQuery(query);
    }

    @Override
    public CartItem getItemWithRelationship(long id) {
        CartItem cartItem = this.findById(id);

        Product product = productService.findById(cartItem.getProductId());

        cartItem.setProduct(product);

        return cartItem;
    }


}
