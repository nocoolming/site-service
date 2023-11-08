package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.model.Role;
import com.ming.site.model.User;
import com.ming.site.repository.RoleRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.RoleService;
import com.ming.site.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl
        extends AbstractService<Role, Long, RoleRepository>
        implements RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void assignRoleToUser(long userId, long roleId) {
        User user = userService.findById(userId);
        Role role = repository.selectById(roleId);

        if (user != null && role != null) {
            user.getRoles().add(role);
            user.setUpgradeAt(LocalDateTime.now());

            userService.update(user);
        }

    }

    @Override
    public List<Role> all(long siteId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.eq("site_id", siteId);
        queryWrapper.orderByDesc("create_at");

        List<Role> roles = repository.selectList(queryWrapper);

        return roles;
    }


}
