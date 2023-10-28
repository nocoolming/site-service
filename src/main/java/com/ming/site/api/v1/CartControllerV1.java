package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Cart;
import com.ming.site.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/cart")
public class CartControllerV1
extends AbstractControllerV1<
        Cart,
        Long,
        CartService> {
    private static final Logger log = LoggerFactory.getLogger(CartControllerV1.class);

    @PostMapping("create")
    Result<Cart> create(@RequestBody Cart cart){
        Cart cartInDb = service.createCart(cart);

        return Result.ok(cartInDb);
    }
}
