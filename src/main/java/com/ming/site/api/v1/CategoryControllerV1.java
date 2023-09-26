package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Category;
import com.ming.site.model.IdEntity;
import com.ming.site.model.Site;
import com.ming.site.model.User;
import com.ming.site.service.CategoryService;
import com.ming.site.service.CrudService;
import com.ming.site.service.SiteService;
import com.ming.site.service.UserService;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/category")
public class  CategoryControllerV1
        extends AbstractControllerV1<
        Category,
        Long,
        CategoryService> {
    private static final Logger log = LoggerFactory.getLogger(CategoryControllerV1.class);

    @Autowired
    UserService userService;
    @Autowired
    SiteService siteService;

    @GetMapping("test")
    Result<Category> test(){
        Category category = new Category();
        category.setId(SnowflakeUtil.nextId());
        category.setTitle("User");
        category.setCode("2-1");
        category.setParentCode("2");

        User createUser = userService.findById(8l).orElse(null);
        User upgradeUser = userService.findById(8l).orElse(null);
        Site site = siteService.findById(8l).orElse(null);

        category.setCreateUser(createUser);
        category.setUpgradeUser(upgradeUser);
        category.setSite(site);

        service.save(category);
        return Result.ok(category);
    }
}
