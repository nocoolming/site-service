package com.ming.site.service;

import com.ming.site.model.CartItem;
import com.ming.site.service.model.AddToCartModel;

import java.util.List;

public interface CartItemService
    extends CrudService<CartItem, Long> {


    CartItem addToCart(CartItem o);

    List<CartItem> getItemsByCartId(long id);

    CartItem getItemWithRelationship(long id);

    List<CartItem> getItemsByIds(List<Long> ids);
}
