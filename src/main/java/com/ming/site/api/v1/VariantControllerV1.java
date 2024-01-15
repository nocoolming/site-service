package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Value;
import com.ming.site.model.Variant;
import com.ming.site.service.NotAllOptionsHaveValuesException;
import com.ming.site.service.VariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("site/v1/variant")
public class VariantControllerV1
        extends AbstractControllerV1<Variant, Long, VariantService> {
    private static final Logger log = LoggerFactory.getLogger(VariantControllerV1.class);

    @PostMapping("buildVariants")
    Result<List<Variant>> buildVariant(@RequestBody long productId) throws NotAllOptionsHaveValuesException {
        List<Variant> list = service.buildVariants(productId);

        return Result.ok(list);
    }

    @GetMapping("productId/{productId}")
    Result<List<Variant>> getVariantsByProductId(@PathVariable long productId) {
        List<Variant> list = service.getVariantsByProductId(productId);

        return Result.ok(list);
    }

    @GetMapping("getValuesMapVariants/productId/{productId}")
    Result<Map<Map<String, Value>, Variant>> getValuesMapVariants(@PathVariable long productId) {
        List<Variant> list = service.getVariantsByProductId(productId);

        Map<Map<String, Value>, Variant> result = new ConcurrentHashMap<>();

        for (Variant variant : list) {
            Map<String, Value> map = new ConcurrentHashMap<>();

            for (Value value : variant.getValues()) {
                map.put(value.getTitle(), value);
            }

            result.put(map, variant);
        }

        return Result.ok(result);
    }
}
