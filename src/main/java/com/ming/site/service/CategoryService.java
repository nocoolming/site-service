package com.ming.site.service;

import com.ming.site.model.Category;

import java.util.List;

public interface CategoryService
        extends CrudService<Category, Long> {

    List<Category> getCategoriesTreeBySiteId(long siteId);

    List<Category> filterChildren(List<Category> allCategories, String parentCode);
}
