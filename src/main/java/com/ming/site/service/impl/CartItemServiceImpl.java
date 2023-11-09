package com.ming.site.service.impl;


import com.ming.site.mapper.CartItemMapper;
import com.ming.site.model.CartItem;
import com.ming.site.model.Product;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CartItemService;
import com.ming.site.service.ProductService;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl
        extends AbstractService<CartItem, Long, CartItemMapper>
        implements CartItemService {

    private static final Logger log
            = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    ProductService productService;

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

        return mapper.selectListByQuery(query);
    }

    @Override
    public CartItem getItemWithRelationship(long id) {
        CartItem cartItem = this.findById(id);

        Product product = productService.findById(cartItem.getProductId());

        cartItem.setProduct(product);

        return cartItem;
    }


}
