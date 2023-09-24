package com.ming.site.repository;

import com.ming.site.model.Cart;
import com.ming.site.model.ProductImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cart", path = "cart")
public interface CartRepository
        extends PagingAndSortingRepository<Cart, Long>, CrudRepository<Cart, Long> {
}
