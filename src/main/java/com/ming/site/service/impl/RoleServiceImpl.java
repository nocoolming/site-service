package com.ming.site.service.impl;


import com.ming.site.mapper.RoleMapper;
import com.ming.site.model.Role;
import com.ming.site.model.User;
import com.ming.site.model.UserRole;
import com.ming.site.service.AbstractService;
import com.ming.site.service.RoleService;
import com.ming.site.service.UserRoleService;
import com.ming.site.service.UserService;
import com.ming.site.util.SnowflakeUtil;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends AbstractService<Role, Long, RoleMapper> implements RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    UserService userService;
    @Resource
    UserRoleService userRoleService;


    @Transactional(propagation = Propagation.REQUIRED)
    public List<Role> initialRoles(long userId) {
        List<Role> roles = new ArrayList<>();

        roles.add(insertRole("Users", userId));
        roles.add(insertRole("Buyers", userId));
        roles.add(insertRole("Works", userId));
        roles.add(insertRole("Roots", userId));
        Role Manager = insertRole("Manager", userId);
        roles.add(Manager);

        UserRole userRole = new UserRole();
        userRole.setRoleId(Manager.getId());
        userRole.setUserId(userId);
        userRoleService.insert(userRole);

        return roles;
    }

    Role insertRole(String title, long userId) {
        Role role = new Role();
        role.setId(SnowflakeUtil.nextId());
        role.setTitle(title);
        role.setUpgradeUserId(userId);
        role.setCreateUserId(userId);

        return this.insert(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void assignRoleToUser(long userId, long roleId) {
        User user = userService.findById(userId);
        Role role = mapper.selectOneById(roleId);

        if (user != null && role != null) {
            user.getRoles().add(role);
            user.setUpgradeAt(LocalDateTime.now());

            userService.update(user);
        }

    }

    @Override
    public List<Role> all(long siteId) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("site_id", siteId);
//        queryWrapper.orderByDesc("create_at");
//
//        List<Role> roles = mapper.selectList(queryWrapper);

        QueryWrapper query = QueryWrapper.create().select().eq("site_id", siteId).orderBy("create_at desc");

        List<Role> roles = mapper.selectListByQuery(query);

        return roles;
    }


}
