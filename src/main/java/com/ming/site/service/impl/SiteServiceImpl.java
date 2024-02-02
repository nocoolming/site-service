package com.ming.site.service.impl;


import com.ming.site.mapper.SiteMapper;
import com.ming.site.model.Category;
import com.ming.site.model.Role;
import com.ming.site.model.Site;
import com.ming.site.model.User;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CategoryService;
import com.ming.site.service.SiteService;
import com.ming.site.service.UserService;
import com.ming.site.util.SnowflakeUtil;
import com.ming.site.util.encrypt.RSAUtil;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SiteServiceImpl
        extends AbstractService<Site, Long, SiteMapper>
        implements SiteService {
    private static final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Resource
    CategoryService categoryService;
    @Resource
    UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Site create(Site site) {
        Site result = super.insert(site);

        User user = new User();
        long userId = 8;
        user.setId(userId);
        user.setUsername("admin");
        user.setPassword(RSAUtil.encrypt("12345678"));
        user.setSiteId(result.getId());
        userService.insert(user);



        Category note = new Category();
        note.setId(SnowflakeUtil.nextId());
        note.setCode("0");
        note.setTitle("note");
        note.setSiteId(result.getId());
        note.setCreateUserId(userId);
        note.setUpgradeUserId(userId);
        categoryService.insert(note);

        Category product = new Category();
        product.setId(SnowflakeUtil.nextId());
        product.setCode("1");
        product.setTitle("product");
        product.setSiteId(result.getId());
        product.setCreateUserId(userId);
        product.setUpgradeUserId(userId);
        categoryService.insert(product);

        Category menu = new Category();
        menu.setId(SnowflakeUtil.nextId());
        menu.setCode("2");
        menu.setTitle("menu");
        menu.setSiteId(result.getId());
        menu.setCreateUserId(userId);
        menu.setUpgradeUserId(userId);
        categoryService.insert(menu);

        Category navbar = new Category();
        navbar.setId(SnowflakeUtil.nextId());
        navbar.setCode("3");
        navbar.setTitle("navbar");
        navbar.setSiteId(result.getId());
        navbar.setCreateUserId(userId);
        navbar.setUpgradeUserId(userId);
        categoryService.insert(navbar);
        return result;
    }




    @Override
    public Site findByDomain(String domain) {
        QueryWrapper query = QueryWrapper.create()
                .select()
                .where("domain=?", domain.toLowerCase());

        Site site = mapper.selectOneByQuery(query);

        return site;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Site insert(Site site) {
        if (site.getId() <= 0) {
            site.setId(System.currentTimeMillis());
        }
        site.setCreateAt(LocalDateTime.now());
        site.setUpgradeAt(LocalDateTime.now());

        mapper.insert(site);
        return site;
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void deleteById(long id){
//        List<Category> categories = categoryService.findAll();
//
//        categories.forEach(c -> categoryService.deleteById(c.getId()));
//
//        List<User>
//        super.deleteById(id);
//    }
}
