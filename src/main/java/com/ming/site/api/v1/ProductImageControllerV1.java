package com.ming.site.api.v1;

import com.ming.site.model.ProductImage;
import com.ming.site.repository.ProductImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/product/image")
public class ProductImageControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(ProductImageControllerV1.class);

//    @Autowired
//    ProductImageRepository productImageRepository;
//
//    @PostMapping("save")
//    ProductImage save(@RequestBody ProductImage productImage){
//        ProductImage result = productImageRepository.save(productImage);
//
//        return result;
//    }
//    Iterable<ProductImage> all(){
//        return productImageRepository.findAll();
//    }
}
