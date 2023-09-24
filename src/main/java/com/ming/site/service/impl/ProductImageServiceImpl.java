package com.ming.site.service.impl;

import com.ming.site.model.ProductImage;
import com.ming.site.repository.ProductImageRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.ProductImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductImageServiceImpl
        extends AbstractService<ProductImage, Long, ProductImageRepository>
        implements ProductImageService {
    private static final Logger log = LoggerFactory.getLogger(ProductImageService.class);
}
