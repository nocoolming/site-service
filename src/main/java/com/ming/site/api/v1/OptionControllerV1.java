package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Option;
import com.ming.site.service.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("site/v1/option")
public class OptionControllerV1
        extends AbstractControllerV1<
        Option,
        Long,
        OptionService> {
    private static final Logger log = LoggerFactory.getLogger(OptionControllerV1.class);

    @GetMapping("productId/{productId}")
    Result<List<Option>> getOptionsByProductId(@PathVariable long productId) {
        List<Option> list = service.getOptionsByProductId(productId);

        return Result.ok(list);
    }
}
