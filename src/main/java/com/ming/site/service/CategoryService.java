package com.ming.site.service;

import com.ming.site.model.Category;

import java.util.List;

public interface CategoryService
        extends CrudService<Category, Long> {

    Category getCategoryBySiteIdAndTitle(long siteId, String title);
    List<Category> getCategoriesTreeBySiteId(long siteId);

    List<Category> getCategoriesTreeBySiteIdAndTitle(long siteId, String title);

    List<Category> filterChildren(List<Category> allCategories, String parentCode);
}
