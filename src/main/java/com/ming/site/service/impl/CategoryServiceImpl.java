package com.ming.site.service.impl;


import com.ming.site.model.Category;
import com.ming.site.repository.CategoryRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl
        extends AbstractService<Category, Long, CategoryRepository>
        implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
}
