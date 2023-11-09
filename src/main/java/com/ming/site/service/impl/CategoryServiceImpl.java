package com.ming.site.service.impl;


import com.ming.site.mapper.CategoryMapper;
import com.ming.site.model.Category;
import com.ming.site.model.Product;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CategoryService;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl
        extends AbstractService<Category, Long, CategoryMapper>
        implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);


    @Override
    public Category getCategoryBySiteIdAndTitle(long siteId, String title) {
//        QueryWrapper query = new QueryWrapper();
//        query.eq("site_id", siteId)
//                .eq("title", title);
//
//        return  this.mapper.selectOne(query);

        return this.mapper.selectOneByCondition(
                QueryCondition
                        .createEmpty()
                        .and("site_id", siteId)
                        .and("title", title)
        );
    }

    @Override
    public List<Category> getCategoriesTreeBySiteId(long siteId) {
        List<Category> categories = this.mapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("site_id", siteId)
        );

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
    public List<Category> getCategoriesTreeBySiteIdAndTitle(long siteId, String title) {
        Category category = this.getCategoryBySiteIdAndTitle(siteId, title);

        List<Category> categories = this.mapper.
                selectListByQuery(
                        QueryWrapper.create()
                                .eq("site_id", siteId)
                );

        List<Category> childrenOfProductCategories =
                categories.stream().filter(c -> c.getParentCode().startsWith(category.getCode())).toList();

        List<Category> roots =
                childrenOfProductCategories.stream()
                        .filter(c -> c.getParentCode().equals(category.getCode())).toList();

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
