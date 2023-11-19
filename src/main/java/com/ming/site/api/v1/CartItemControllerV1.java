package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.CartItem;
import com.ming.site.service.CartItemService;
import com.ming.site.service.model.AddToCartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("site/v1/cartItem")
public class CartItemControllerV1
extends AbstractControllerV1<
        CartItem,
        Long,
        CartItemService> {
    private static final Logger log
            = LoggerFactory.getLogger(CartItemControllerV1.class);


    @GetMapping("getItemsByCartId/{cartId}")
    Result<List<CartItem>> getItemsByCartId(@PathVariable long cartId){
        List<CartItem> list = service.getItemsByCartId(cartId);
        return Result.ok(list);
    }

    @PostMapping("addToCart")
    Result<CartItem> addToCart(@RequestBody CartItem o){
        return Result.ok(service.addToCart(o));
    }



}
