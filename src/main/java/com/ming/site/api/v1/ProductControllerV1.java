package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Product;
import com.ming.site.repository.ProductRepository;
import com.ming.site.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/product")
public class ProductControllerV1
        extends AbstractControllerV1<
        Product,
        Long,
        ProductService> {
    private static final Logger log = LoggerFactory.getLogger(ProductControllerV1.class);


}
