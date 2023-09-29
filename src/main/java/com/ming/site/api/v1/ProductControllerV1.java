package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Product;
import com.ming.site.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("site/v1/product")
public class ProductControllerV1
        extends AbstractControllerV1<
        Product,
        Long,
        ProductService> {
    private static final Logger log = LoggerFactory.getLogger(ProductControllerV1.class);


    @GetMapping("siteId/{siteId}/beginTime/{begin}")
    public Result<List<Product>> site(
            @PathVariable long siteId,
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime begin) {

        log.info(begin.toString());

        List<Product> products = service.findAll(begin);
        return Result.ok(products);
    }
}
