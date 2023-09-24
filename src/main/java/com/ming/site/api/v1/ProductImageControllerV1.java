package com.ming.site.api.v1;

import com.ming.site.model.ProductImage;
import com.ming.site.repository.ProductImageRepository;
import com.ming.site.service.ProductImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("site/v1/product/image")
public class ProductImageControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(ProductImageControllerV1.class);

    @Autowired
    ProductImageService productImageService;

    @PostMapping("save")
    ProductImage save(@RequestBody ProductImage productImage){
        productImage.setCreateAt(LocalDateTime.now());
        ProductImage result = productImageService.save(productImage);

        log.debug(productImage.toString());
        return result;
    }

    @GetMapping("all")
    Iterable<ProductImage> all(){
        return productImageService.findAll();
    }
}
