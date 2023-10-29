package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.model.CartItem;
import com.ming.site.repository.CartItemRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl
        extends AbstractService<CartItem, Long, CartItemRepository>
        implements CartItemService {


    @Override
    public List<CartItem> getItemsByCartId(long id) {
        QueryWrapper<CartItem> query = new QueryWrapper<>();

        query.eq("cart_id", id)
                .orderByDesc("upgrade_at");


        return repository.selectList(query);
    }
}
