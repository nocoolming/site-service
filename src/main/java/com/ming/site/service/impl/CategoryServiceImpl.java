package com.ming.site.service.impl;



import com.ming.site.model.Category;
import com.ming.site.model.Product;
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
    public Category getCategoryBySiteIdAndTitle(long siteId, String title) {
        QueryWrapper query = new QueryWrapper();
        query.eq("site_id", siteId)
                .eq("title", title);

        return repository.selectOne(query);
    }

    @Override
    public List<Category> getCategoriesTreeBySiteId(long siteId) {
        QueryWrapper query = new QueryWrapper();
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
    public List<Category> getCategoriesTreeBySiteIdAndTitle(long siteId, String title) {
        Category category = this.getCategoryBySiteIdAndTitle(siteId, title);

        QueryWrapper query = new QueryWrapper();
        query.eq("site_id", siteId);
//                .like("parent_code", category.getCode() );

        List<Category> categories = repository.selectList(query);

        List<Category> childrenOfProductCategories =
                categories.stream().filter(c -> c.getParentCode().startsWith(category.getCode())).toList();

        List<Category> roots =
                childrenOfProductCategories.stream()
                        .filter(c -> c.getParentCode().equals(category.getCode()) ).toList();

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
