package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.ProductImage;
import com.ming.site.service.ProductImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("site/v1/product/image")
public class ProductImageControllerV1
        extends AbstractControllerV1<
        ProductImage, Long,
        ProductImageService> {
    private static final Logger log = LoggerFactory.getLogger(ProductImageControllerV1.class);

    @GetMapping("productId/{productId}")
    public List<ProductImage> getImagesByProductId(@PathVariable long productId){
        return service.getImagesByProductId(productId);
    }

}
