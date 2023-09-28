package com.ming.site.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.model.Category;
import com.ming.site.repository.CategoryRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl
        extends AbstractService<Category, Long, CategoryRepository>
        implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);


    @Override
    public List<Category> getCategoriesTreeBySiteId(long siteId) {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query.eq("site_id", siteId);

        List<Category> categories = repository.selectList(query);

        List<Category> roots =
                categories.stream()
                        .filter(
                                c -> c.getParentCode() == null || c.getParentCode().isEmpty()).toList();


        roots.forEach(root -> {
            List<Category> children =
                    this.filterChildren(categories, root.getCode());
            root.setChildren(children);
        });
        return roots;
    }

    @Override
    public List<Category> filterChildren(List<Category> allCategories, String parentCode) {
        List<Category> categories =
                allCategories.stream().filter(
                        c ->
                                c.getParentCode() != null &&
                                        c.getParentCode().equals(parentCode)).toList();

        categories.forEach(
                parent -> {
                    List<Category> children =
                            this.filterChildren(allCategories, parent.getCode());

                    parent.setChildren(children);
                }
        );
        return categories;
    }
}
