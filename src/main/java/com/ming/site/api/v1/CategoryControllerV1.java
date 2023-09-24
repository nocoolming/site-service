package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Category;
import com.ming.site.model.IdEntity;
import com.ming.site.service.CategoryService;
import com.ming.site.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/category")
public class CategoryControllerV1
        extends AbstractControllerV1<
        Category,
        Long,
        CategoryService> {
    private static final Logger log = LoggerFactory.getLogger(CategoryControllerV1.class);
}
