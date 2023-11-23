package com.ming.site.service.impl;


import com.ming.site.mapper.CategoryMapper;
import com.ming.site.model.CartItem;
import com.ming.site.model.Category;
import com.ming.site.model.Product;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CategoryService;
import com.ming.site.util.SnowflakeUtil;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl
        extends AbstractService<Category, Long, CategoryMapper>
        implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Category insert(Category o) {
        o.setId(SnowflakeUtil.nextId());
        o.setCreateAt(LocalDateTime.now());
        o.setUpgradeAt(LocalDateTime.now());
        this.mapper.insertSelective(o);

        return o;
    }

    @Override
    public Category getCategoryBySiteIdAndTitle(long siteId, String title) {
//        QueryWrapper query = new QueryWrapper();
//        query.eq("site_id", siteId)
//                .eq("title", title);
//
//        return  this.mapper.selectOne(query);

        return this.mapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("site_id", siteId)
                        .and("title=?", title)
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
        List<Category> categories = this.mapper.
                selectListByQuery(
                        QueryWrapper.create()
                                .eq("site_id", siteId)
                );

        Category category = categories.stream().filter(c -> c.getTitle().equals(title)).findFirst().get();
        if (category == null) {
            return null;
        }

        List<Category> childrenOfTitleCategories =
                categories.stream().filter(c -> c.getParentCode().startsWith(category.getCode())).toList();

        List<Category> roots =
                childrenOfTitleCategories.stream()
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
