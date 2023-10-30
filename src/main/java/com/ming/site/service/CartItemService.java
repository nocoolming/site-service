package com.ming.site.service;

import com.ming.site.model.CartItem;

import java.util.List;

public interface CartItemService
    extends CrudService<CartItem, Long> {

    List<CartItem> getItemsByCartId(long id);

    CartItem getItemWithRelationship(long id);
}
