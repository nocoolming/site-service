package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Variant;
import com.ming.site.service.NotAllOptionsHaveValuesException;
import com.ming.site.service.VariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("site/v1/variant")
public class VariantControllerV1 extends AbstractControllerV1<Variant, Long, VariantService> {
    private static final Logger log = LoggerFactory.getLogger(VariantControllerV1.class);

    @PostMapping("buildVariants")
    Result<List<Variant>> buildVariant(@RequestBody long productId) throws NotAllOptionsHaveValuesException {
        List<Variant> list = service.buildVariants(productId);

        return Result.ok(list);
    }

}
