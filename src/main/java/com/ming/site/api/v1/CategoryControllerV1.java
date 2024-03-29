package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.Category;
import com.ming.site.model.Role;
import com.ming.site.model.Site;
import com.ming.site.model.User;
import com.ming.site.service.CategoryService;
import com.ming.site.service.SiteService;
import com.ming.site.service.UserService;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @GetMapping("tree/{siteId}")
    Result<List<Category>> tree(@PathVariable long siteId){
        List<Category> roots = service.getCategoriesTreeBySiteId(siteId);

        return Result.ok(roots);
    }

    @GetMapping("tree/siteId/{siteId}/title/{title}")
    Result<List<Category>> getCategoriesTreeBySiteIdAndTitle(
            @PathVariable long siteId,
            @PathVariable String title){
        List<Category> roots = service.getCategoriesTreeBySiteIdAndTitle(siteId, title);

        return Result.ok(roots);
    }

    @GetMapping("/siteId/{siteId}/title/{title}")
    Result<Category> getCategoryBySiteIdAndTitle(
            @PathVariable long siteId,
            @PathVariable String title){
        Category category = service.getCategoryBySiteIdAndTitle(siteId, title);

        return Result.ok(category);
    }


}
