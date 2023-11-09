package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.ming.site.model.Product;
import com.ming.site.model.ProductImage;
import com.ming.site.repository.ProductImageRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.ProductImageService;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductImageServiceImpl extends AbstractService<ProductImage, Long, ProductImageRepository> implements ProductImageService {
    private static final Logger log = LoggerFactory.getLogger(ProductImageService.class);


    @Override
    public List<ProductImage> getImagesByProductId(long productId) {
        QueryWrapper query = new QueryWrapper();
        query.eq("product_id", productId);

        List<ProductImage> images = repository.selectList(query);


        return images;
    }
}
