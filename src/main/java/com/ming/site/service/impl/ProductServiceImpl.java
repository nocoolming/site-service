package com.ming.site.service.impl;

import com.ming.site.model.Product;
import com.ming.site.repository.ProductRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl
        extends AbstractService<Product, Long, ProductRepository>
        implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
}
