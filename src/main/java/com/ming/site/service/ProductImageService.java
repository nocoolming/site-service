package com.ming.site.service;

import com.ming.site.model.ProductImage;

import java.util.List;

public interface ProductImageService
        extends CrudService<ProductImage, Long> {

    List<ProductImage> getImagesByProductId(long productId);
}
