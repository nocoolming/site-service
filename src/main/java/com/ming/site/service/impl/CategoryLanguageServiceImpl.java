package com.ming.site.service.impl;

import com.ming.site.mapper.CategoryLanguageMapper;
import com.ming.site.model.CartItem;
import com.ming.site.model.CategoryLanguage;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CategoryLanguageService;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CategoryLanguageServiceImpl
        extends AbstractService<CategoryLanguage, Long, CategoryLanguageMapper>
        implements CategoryLanguageService {
    private  static final Logger log = LoggerFactory.getLogger(CategoryLanguageServiceImpl.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CategoryLanguage insert(CategoryLanguage o){
        o.setId(SnowflakeUtil.nextId());
        o.setCreateAt(LocalDateTime.now());
        o.setUpgradeAt(LocalDateTime.now());
        this.mapper.insertSelective(o);

        return o;
    }
}
