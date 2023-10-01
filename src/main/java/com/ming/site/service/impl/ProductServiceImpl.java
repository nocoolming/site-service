package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.model.*;
import com.ming.site.repository.ProductRepository;
import com.ming.site.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl extends AbstractService<Product, Long, ProductRepository> implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    SiteService siteService;
    @Autowired
    ProductImageService productImageService;


    @Override
    public Product findById(long id) {
        Product product = super.findById(id);

        return this.loadForeignField(product);
    }

    @Override
    public List<Product> findAll() {
        return this.loadForeign(super.findAll());
    }

    public List<Product> findAll(LocalDateTime begin) {
        List<Product> products = null;

        QueryWrapper<Product> query = new QueryWrapper<>();
        query.lt("upgrade_at", begin);
        query.orderByDesc("upgrade_at");

        products = repository.selectList(query);


        return this.loadForeign(products);
    }


    @Override
    public List<Product> loadForeign(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return null;
        }

        for (Product product : products) {
            product = this.loadForeignField(product);
        }
        return products;
    }

    @Override
    public Product loadForeignField(Product product) {
        if (product == null) {
            return null;
        }

        Category category = categoryService.findById(product.getCategoryId());
        User createUser = userService.findById(product.getCreateUserId());
        User upgradeUser = userService.findById(product.getUpgradeUserId());
        Site site = siteService.findById(product.getSiteId());
        List<ProductImage> images = productImageService.getImagesByProductId(product.getId());

        product.setCategory(category);
        product.setCreateUser(createUser);
        product.setUpgradeUser(upgradeUser);
        product.setSite(site);
        product.setProductImageList(images);

        return product;
    }
}
