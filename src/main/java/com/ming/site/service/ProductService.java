package com.ming.site.service;

import com.ming.site.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService
        extends CrudService<Product, Long> {
    List<Product> findAll(LocalDateTime begin);

    Product getProductWithRelationShip(long id);

    List<Product> loadForeign(List<Product> products);

    Product loadForeignField(Product product);

    Product setIcon(long id, String icon) throws ProductNotFoundException;
}
