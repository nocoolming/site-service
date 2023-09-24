package com.ming.site.service.impl;

import com.ming.site.model.CategoryLanguage;
import com.ming.site.repository.CategoryLanguageRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CategoryLanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryLanguageServiceImpl
        extends AbstractService<CategoryLanguage, Long, CategoryLanguageRepository>
        implements CategoryLanguageService {
    private  static final Logger log = LoggerFactory.getLogger(CategoryLanguageServiceImpl.class);
}
