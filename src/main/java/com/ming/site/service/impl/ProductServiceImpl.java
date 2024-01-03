package com.ming.site.service.impl;


import com.ming.site.mapper.ProductMapper;
import com.ming.site.model.*;
import com.ming.site.service.*;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl
        extends AbstractService<Product, Long, ProductMapper>
        implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    SiteService siteService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    VariantService variantService;


    @Override
    public List<Product> findAll() {
        return this.loadForeign(super.findAll());
    }

    public List<Product> findAll(LocalDateTime begin) {
        List<Product> products = null;

        products = this.mapper.selectListByQuery(
                QueryWrapper.create()
                        .lt("upgrade_at", begin)
                        .orderBy("upgrade_at desc")
        );
        return this.loadForeign(products);
    }

    @Override
    public Product getProductWithRelationShip(long id) {
        Product product = this.findById(id);

        return this.loadForeignField(product);
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

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Product setIcon(long id, String icon) throws ProductNotFoundException {
        Product product = this.findById(id);

        if (product == null) {
            throw new ProductNotFoundException();
        }
        product.setUpgradeAt(LocalDateTime.now());
        product.setIcon(icon);

        this.update(product);
        return product;
    }
}
